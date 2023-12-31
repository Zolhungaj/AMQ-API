package tech.zolhungaj.amqapi;


import com.squareup.moshi.*;
import com.squareup.moshi.adapters.PolymorphicJsonAdapterFactory;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import tech.zolhungaj.amqapi.adapters.IntegerEnumJsonAdapter;
import tech.zolhungaj.amqapi.client.Client;
import tech.zolhungaj.amqapi.client.DummyClient;
import tech.zolhungaj.amqapi.clientcommands.*;
import tech.zolhungaj.amqapi.constants.AmqRanked;
import tech.zolhungaj.amqapi.servercommands.*;
import tech.zolhungaj.amqapi.servercommands.globalstate.*;
import tech.zolhungaj.amqapi.servercommands.objects.AvatarPose;
import tech.zolhungaj.amqapi.servercommands.objects.ListStatus;
import tech.zolhungaj.amqapi.servercommands.objects.PlayerStatus;
import tech.zolhungaj.amqapi.servercommands.objects.SongType;
import tech.zolhungaj.amqapi.servercommands.objects.expand.ExpandSongStatus;
import tech.zolhungaj.amqapi.servercommands.store.TicketRollResult;
import tech.zolhungaj.amqapi.sharedobjects.AnimeList;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.time.Duration;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.TimeoutException;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
public class AmqApi implements Runnable{
    private static final Moshi MOSHI = new Moshi
            .Builder()
            .add(new CustomBooleanAdapter())
            .add(new CustomOptionalBooleanAdapter())
            .add(new CustomOptionalStringAdapter())
            .add(new CustomLocalDateAdapter())
            .add(new CustomOffsetDateTimeAdapter())
            .add(new OptionalFactory())
            .add(PolymorphicJsonAdapterFactory.of(TicketRollResult.Reward.class, "rewardType")
                    .withSubtype(TicketRollResult.SkinReward.class, "avatar")
                    .withSubtype(TicketRollResult.ColorReward.class, "color")
                    .withSubtype(TicketRollResult.EmoteReward.class, "emote")
            )
            .add(PlayerStatus.class, IntegerEnumJsonAdapter.create(PlayerStatus.class))
            .add(NewQuestEvents.QuestEventState.class, IntegerEnumJsonAdapter.create(NewQuestEvents.QuestEventState.class))
            .add(AmqRanked.RankedSeries.class, IntegerEnumJsonAdapter.create(AmqRanked.RankedSeries.class))
            .add(AmqRanked.RankedState.class, IntegerEnumJsonAdapter.create(AmqRanked.RankedState.class))
            .add(ExpandSongStatus.class, IntegerEnumJsonAdapter.create(ExpandSongStatus.class).withUnknownFallback(ExpandSongStatus.UNKNOWN))
            .add(SongType.class, IntegerEnumJsonAdapter.create(SongType.class).withUnknownFallback(SongType.UNKNOWN))
            .add(AnimeList.class, IntegerEnumJsonAdapter.create(AnimeList.class))
            .add(AvatarPose.class, IntegerEnumJsonAdapter.create(AvatarPose.class))
            .add(ListStatus.class, IntegerEnumJsonAdapter.create(ListStatus.class))
            .add(LoginComplete.QuestDescription.QuestStateWeekSlot.class, new LoginComplete.QuestDescription.QuestStateWeekSlotAdapter())
            .addLast(new com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory())
            .build();
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
        log.debug("{}, {}", command, command.getClass());
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
        interceptJsonList.forEach(consumer -> consumer.accept(data));
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
            return MOSHI.adapter(clazz).fromJson(data.toString());
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


    private static class CustomOptionalBooleanAdapter extends JsonAdapter<Optional<Boolean>> {
        @Override
        @FromJson
        public Optional<Boolean> fromJson(JsonReader reader) throws IOException {
            return switch(reader.peek()){
                case BOOLEAN -> Optional.of(reader.nextBoolean());
                case NUMBER -> {
                    int i = reader.nextInt();
                    if(i == 0){
                        yield Optional.of(false);
                    }else if(i == 1){
                        yield Optional.of(true);
                    }else{
                        throw new JsonDataException("Integer " + i + " is out of range [0-1]");
                    }
                }
                case NULL -> {
                    reader.nextNull();
                    yield Optional.empty();
                }
                default -> throw new JsonDataException("Expected an Integer but got a " + reader.peek().name());
            };
        }

        @Override
        @ToJson
        public void toJson(JsonWriter writer, Optional<Boolean> value) throws IOException {
            if(Objects.requireNonNull(value).isPresent()){
                writer.value(Boolean.TRUE.equals(value.get()) ? 1 : 0);
            }else{
                writer.nullValue();
            }
        }
    }

    private static class CustomBooleanAdapter extends JsonAdapter<Boolean> {
        @Override
        @FromJson
        public Boolean fromJson(JsonReader reader) throws IOException {
            return switch(reader.peek()){
                case BOOLEAN -> reader.nextBoolean();
                case NUMBER -> {
                    int i = reader.nextInt();
                    if(i == 0){
                        yield false;
                    }else if(i==1){
                        yield true;
                    }else{
                        throw new JsonDataException("Integer " + i + " is out of range [0-1]");
                    }
                }
                case NULL -> {
                    reader.nextNull();
                    yield false;
                }
                default -> throw new JsonDataException("Expected an Integer but got a " + reader.peek().name());
            };
        }

        @Override
        @ToJson
        public void toJson(JsonWriter writer, Boolean value) throws IOException {
            writer.value(Boolean.TRUE.equals(value) ? 1 : 0);
        }
    }

    private static class CustomLocalDateAdapter extends JsonAdapter<LocalDate> {
        private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        @Override
        @FromJson
        public LocalDate fromJson(JsonReader reader) throws IOException {
            if(reader.peek() == JsonReader.Token.STRING){
                return LocalDate.parse(reader.nextString(), FORMATTER);
            }else{
                throw new JsonDataException("Expected an String, but got a " + reader.peek().name());
            }
        }

        @Override
        @ToJson
        public void toJson(JsonWriter writer, LocalDate value) throws IOException {
            writer.value(FORMATTER.format(value));
        }
    }

    private static class CustomOffsetDateTimeAdapter extends JsonAdapter<OffsetDateTime> {
        private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ISO_OFFSET_DATE_TIME;
        @Override
        @FromJson
        public OffsetDateTime fromJson(JsonReader reader) throws IOException {
            if(reader.peek() == JsonReader.Token.STRING){
                return OffsetDateTime.parse(reader.nextString(), FORMATTER);
            }else{
                throw new JsonDataException("Expected an String, but got a " + reader.peek().name());
            }
        }

        @Override
        @ToJson
        public void toJson(JsonWriter writer, OffsetDateTime value) throws IOException {
            writer.value(FORMATTER.format(value));
        }
    }

    //uber cursed to fix the server sending a false boolean instead of a string in like one place
    private static class CustomOptionalStringAdapter extends JsonAdapter<Optional<String>> {
        @Override
        @FromJson
        public Optional<String> fromJson(JsonReader reader) throws IOException {
            return switch(reader.peek()){
                case STRING -> Optional.of(reader.nextString());
                case NULL -> {
                    reader.nextNull();
                    yield Optional.empty();
                }
                case BOOLEAN -> {
                    boolean cursedBoolean = reader.nextBoolean();
                    if(!cursedBoolean){
                        yield Optional.empty();
                    }
                    throw new JsonDataException("Expected a false but got a true");
                }
                default -> throw new JsonDataException("Expected a Boolean or String but got a " + reader.peek().name());
            };
        }

        @Override
        @ToJson
        public void toJson(JsonWriter writer, Optional<String> value) throws IOException {
            if(Objects.requireNonNull(value).isPresent()){
                writer.value(value.get());
            }else{
                writer.nullValue();
            }
        }
    }

    private static class OptionalFactory implements JsonAdapter.Factory {
        @Override
        public JsonAdapter<?> create(Type type, Set<? extends Annotation> annotations, Moshi moshi) {
            if (!annotations.isEmpty()) return null;
            if (!(type instanceof ParameterizedType)) return null;

            Class<?> rawType = Types.getRawType(type);
            if (rawType != Optional.class) return null;

            Type optionalType = ((ParameterizedType) type).getActualTypeArguments()[0];

            JsonAdapter<?> optionalTypeAdapter = moshi.adapter(optionalType).nullSafe();

            return new OptionalJsonAdapter<>(optionalTypeAdapter);
        }

        private static class OptionalJsonAdapter<T> extends JsonAdapter<Optional<T>> {

            private final JsonAdapter<T> optionalTypeAdapter;

            public OptionalJsonAdapter(JsonAdapter<T> optionalTypeAdapter) {
                this.optionalTypeAdapter = optionalTypeAdapter;
            }

            @Override
            public Optional<T> fromJson(JsonReader reader) throws IOException {
                T instance = optionalTypeAdapter.fromJson(reader);
                return Optional.ofNullable(instance);
            }

            @Override
            public void toJson(JsonWriter writer, Optional<T> value) throws IOException {
                if(Objects.isNull(value)){
                    writer.nullValue();
                }
                else if (value.isPresent()) {
                    optionalTypeAdapter.toJson(writer, value.get());
                } else {
                    writer.nullValue();
                }
            }
        }
    }
}

