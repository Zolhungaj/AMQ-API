package tech.zolhungaj.amqapi;


import com.squareup.moshi.*;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import tech.zolhungaj.amqapi.adapters.*;
import tech.zolhungaj.amqapi.client.Client;
import tech.zolhungaj.amqapi.client.DummyClient;
import tech.zolhungaj.amqapi.clientcommands.*;
import tech.zolhungaj.amqapi.servercommands.*;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.lang.reflect.*;
import java.time.Duration;
import java.util.*;
import java.util.concurrent.TimeoutException;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
public class AmqApi implements Runnable{
    private final Moshi moshi;
    private final Map<String, Class<@CommandType ?>> nameToClassMap = new HashMap<>();
    private final Map<Class<@CommandType ?>, List<Consumer<@CommandType ?>>> onMap = new HashMap<>();
    private final Map<Class<@CommandType ?>, List<Predicate<@CommandType ?>>> onceMap = new HashMap<>();
    private final Map<String, List<Consumer<JSONObject>>> interceptJsonMap = new HashMap<>();
    private final List<Consumer<Object>> interceptCommandList = new ArrayList<>();
    private final List<Consumer<JSONObject>> interceptJsonList = new ArrayList<>();

    private final String username;

    private final String password;

    private final boolean forceConnect;

    private Client client;

    public AmqApi(String username, String password, boolean forceConnect) {
        this.username = username;
        this.password = password;
        this.forceConnect = forceConnect;
        moshi = new MoshiFactory().create();
    }

    public void registerCommand(Class<@CommandType ?> clazz){
        if(!clazz.isAnnotationPresent(CommandType.class)){
            throw new IllegalArgumentException("Class " + clazz + " does not have a CommandType annotation");
        }
        Stream.of(clazz.getAnnotationsByType(CommandType.class))
                .map(CommandType::value)
                .forEach(commandName -> this.register(commandName, clazz));
    }

    private <@CommandType T> void register(String commandName, Class<T> clazz){
        Class<?> currentClass = nameToClassMap.get(commandName);
        if(currentClass != null && !currentClass.equals(clazz)){
            throw new IllegalArgumentException("Command " + commandName + " is already registered to " + currentClass);
        }
        nameToClassMap.put(commandName, clazz);
    }

    public void sendCommand(@NonNull @CommandGroup @CommandName Object command){
        Class<?> clazz = command.getClass();
        final String group;
        if(clazz.isAnnotationPresent(CommandGroup.class)) {
            group = clazz.getAnnotation(CommandGroup.class).value();
        }else{
            Set<String> types = Stream.of(clazz.getInterfaces())
                    .filter(c -> c.isAnnotationPresent(CommandGroup.class))
                    .map(c -> c.getAnnotation(CommandGroup.class))
                    .map(CommandGroup::value)
                    .collect(Collectors.toSet());
            if(types.isEmpty()){
                throw new IllegalArgumentException("No CommandGroup annotation found on " + clazz + " or its interfaces");
            }
            if(types.size() > 1){
                throw new IllegalArgumentException("Multiple CommandGroup annotations found on " + clazz + " or its interfaces");
            }
            group = types.iterator().next();
        }
        String commandName = clazz.getAnnotation(CommandName.class).value();

        if(clazz.isAnnotationPresent(EmptyClientCommand.class)){
            this.client.sendCommand(group, commandName, null);
        }else if(clazz.isAnnotationPresent(DirectDataCommand.class)){
            this.client.sendCommand(group, commandName, extractDirectData(command));
        }else{
            this.client.sendCommand(group, commandName, command);
        }
    }

    private Object extractDirectData(@NonNull Object command){
        Class<?> clazz = command.getClass();
        DirectDataCommand ddc = clazz.getAnnotation(DirectDataCommand.class);
        String fieldName = ddc.value();
        RecordComponent[] recordComponents = clazz.getRecordComponents();
        if(recordComponents != null){
            for(RecordComponent recordComponent : recordComponents){
                if(recordComponent.getName().equals(fieldName)){
                    try{
                        return recordComponent.getAccessor().invoke(command);
                    }catch (IllegalAccessException | InvocationTargetException e){
                        throw new IllegalArgumentException("Could not access record component " + fieldName + " in " + clazz,e);
                    }
                }
            }
        }
        try{
            String getterName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
            Method method = clazz.getMethod(getterName);
            return method.invoke(command);
        } catch (InvocationTargetException | IllegalAccessException e) {
            throw new IllegalArgumentException("Could not access getter for " + fieldName + " in " + clazz,e);
        } catch(NoSuchMethodException e){
            //ignoring so we can try the other options
        }
        try{
            Method method = clazz.getMethod(fieldName);
            return method.invoke(command);
        } catch (InvocationTargetException | IllegalAccessException e) {
            throw new IllegalArgumentException("Could not access method " + fieldName + " in " + clazz,e);
        } catch(NoSuchMethodException e){
            //ignoring so we can try the other options
        }
        try{
            Field field = command.getClass().getDeclaredField(fieldName);
            return field.get(command);
        }catch (NoSuchFieldException e){
            //ignoring so we can try the other options
        }catch (IllegalAccessException e){
            throw new IllegalArgumentException("Could not access field " + fieldName + " in " + clazz,e);
        }
        throw new IllegalArgumentException("Could not find field/component/method '" + fieldName + "' in " + clazz);
    }

    public <@CommandType T> void on(Class<T> clazz, Consumer<T> consumer){
        registerCommand(clazz);
        this.onMap.putIfAbsent(clazz, new ArrayList<>());
        this.onMap.get(clazz).add(consumer);
    }

    public <@CommandType T> void once(Class<T> clazz, Predicate<T> predicate){
        registerCommand(clazz);
        this.onceMap.putIfAbsent(clazz, new ArrayList<>());
        this.onceMap.get(clazz).add(predicate);
    }



    public void onJson(String command, Consumer<JSONObject> consumer){
        this.interceptJsonMap.putIfAbsent(command, new ArrayList<>());
        this.interceptJsonMap.get(command).add(consumer);
    }

    public void onAllJson(Consumer<JSONObject> consumer){
        this.interceptJsonList.add(consumer);
    }

    public void onAllCommands(Consumer<@CommandType Object> consumer){
        this.interceptCommandList.add(consumer);
    }

    /** Trigger every EventHandler with the provided Command.
     * Usage from outside this class is primarily intended to be for testing/debugging purposes.
     *
     * @param command the Command to be handled
     */
    @SuppressWarnings("unchecked")
    public void handle(@CommandType Object command){
        log.info("{}, {}", command, command.getClass());
        interceptCommandList.forEach(c -> c.accept(command));
        if(onMap.containsKey(command.getClass())){
            List<Consumer<@CommandType ?>> consumers = onMap.get(command.getClass());
            for(Consumer<@CommandType ?> consumer : consumers){
                Consumer<Object> wayBetterConsumer = (Consumer<Object>) consumer;
                wayBetterConsumer.accept(command);
            }
        }
        if(onceMap.containsKey(command.getClass())){
            List<Predicate<?>> predicates = onceMap.get(command.getClass());
            Iterator<Predicate<?>> iterator = predicates.iterator();
            while(iterator.hasNext()){
                Predicate<?> predicate = iterator.next();
                Predicate<Object> wayBetterPredicate = (Predicate<Object>) predicate;
                boolean result = wayBetterPredicate.test(command);
                if(result){
                    iterator.remove();
                }
            }
        }
    }

    private Object serverCommandToCommand(Client.ServerCommand serverCommand) throws IOException {
        String commandName = serverCommand.command();
        JSONObject data = serverCommand.data();
        if(interceptJsonMap.containsKey(commandName)){
            interceptJsonMap.get(commandName).forEach(consumer -> consumer.accept(data));
        }
        interceptJsonList.forEach(consumer -> {
            JSONObject object = new JSONObject();
            object.put("command", commandName);
            object.put("data", data);
            consumer.accept(object);
        });
        Class<?> clazz = nameToClassMap.get(commandName);
        log.debug("""
                ServerCommand: {}
                data: {}
                class: {}
                """, serverCommand, data, clazz);
        if(clazz == null){
            return new UnregisteredCommand(commandName, data);
        }
        try{
            return moshi.adapter(clazz).fromJson(data.toString());
        }catch(JsonDataException e){
            log.warn("Something went wrong", e);
            return new ErrorParsingCommand(commandName, data, e);
        }
    }

    @Override
    public void run() {
        try(var newClient = new Client(username, password)){
            newClient.start(this.forceConnect);
            this.client = newClient;
            while(!Thread.interrupted()){
                Client.ServerCommand serverCommand = client.pollCommand(Duration.ofMinutes(5));
                Object command = serverCommandToCommand(serverCommand);
                if(command == null){
                    continue;
                }
                handle(command);
            }
        } catch (InterruptedException e) {
            log.error("AMQ-API Interrupted, ", e);
            Thread.currentThread().interrupt();
        } catch (IOException e){
            throw new UncheckedIOException(e);
        } catch (TimeoutException e){
            log.error("Timed out", e);
        }
    }

    public DummyClient startDummy(){
        var dummyClient = new DummyClient();
        this.client = dummyClient;
        return dummyClient;
    }
}

