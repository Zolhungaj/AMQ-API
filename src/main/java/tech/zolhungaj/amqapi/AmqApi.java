package tech.zolhungaj.amqapi;


import com.squareup.moshi.*;
import io.micrometer.core.lang.Nullable;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tech.zolhungaj.amqapi.client.Client;
import tech.zolhungaj.amqapi.commands.*;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.time.Duration;
import java.util.*;

public class AmqApi implements Runnable{
    private static final Logger LOG = LoggerFactory.getLogger(AmqApi.class);
    private static final Moshi MOSHI = new Moshi
            .Builder()
            .add(new CustomBooleanAdapter())
            .add(new CustomOptionalBooleanAdapter())
            .add(new OptionalFactory())
            .build();
    private final List<EventHandler> onList = new ArrayList<>();
    private final List<EventHandler> onceList = new ArrayList<>();

    private final String username;

    private final String password;

    private final boolean forceConnect;

    public AmqApi(String username, String password, boolean forceConnect) {
        this.username = username;
        this.password = password;
        this.forceConnect = forceConnect;
    }

    public void on(EventHandler event){
        this.onList.add(event);
    }
    public void once(EventHandler event){
        this.onceList.add(event);
    }

    private void handle(Command command){
        LOG.info("{}, {}, {}", command, command.getClass(), command.getName());

        onList
                .forEach(c -> c.call(command));
        onceList.removeIf(eventHandler -> eventHandler.call(command));
    }

    private Command serverCommandToCommand(Client.ServerCommand serverCommand) throws IOException {
        String commandName = serverCommand.command();
        JSONObject data = serverCommand.data();
        CommandType commandType = CommandType.forName(commandName);
        LOG.info("""
                ServerCommand: {}
                data: {}
                commandType: {}
                """, serverCommand, data, commandType);
        if(commandType == null){
            LOG.info("""
                    Unknown command:
                        command: {}
                        data: {}
                    """, commandName, data);
            return null;
        }
        String dataAsString = data.toString();
        return switch(commandType){
            case CHAT_MESSAGES -> MOSHI.adapter(GameChatUpdate.class).fromJson(dataAsString);
            case GAME_INVITE -> MOSHI.adapter(GameInvite.class).fromJson(dataAsString);
            case ONLINE_PLAYERS -> MOSHI.adapter(OnlinePlayerCountChange.class).fromJson(dataAsString);
            case LOGIN_COMPLETE -> MOSHI.adapter(LoginComplete.class).fromJson(dataAsString);
        };
    }


    @Override
    public void run() {
        try(var client = new Client(username, password, forceConnect)){
            while(!Thread.interrupted()){
                Client.ServerCommand serverCommand = client.pollCommand(Duration.ofMinutes(1));
                Command command = serverCommandToCommand(serverCommand);
                if(command == null){
                    continue;
                }
                handle(command);
            }
        } catch (InterruptedException e) {
            LOG.error("AMQ-API Interrupted, ", e);
            Thread.currentThread().interrupt();
        } catch (IOException e){
            throw new UncheckedIOException(e);
        }
    }
}

class CustomOptionalBooleanAdapter extends JsonAdapter<Optional<Boolean>> {
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

class CustomBooleanAdapter extends JsonAdapter<Boolean> {
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
            default -> throw new JsonDataException("Expected an Integer but got a " + reader.peek().name());
        };
    }

    @Override
    @ToJson
    public void toJson(JsonWriter writer, Boolean value) throws IOException {
        writer.value(Boolean.TRUE.equals(value) ? 1 : 0);
    }
}

class OptionalFactory implements JsonAdapter.Factory {
    @Nullable
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

        @Nullable
        @Override
        public Optional<T> fromJson(JsonReader reader) throws IOException {
            T instance = optionalTypeAdapter.fromJson(reader);
            return Optional.ofNullable(instance);
        }

        @Override
        public void toJson(JsonWriter writer, @Nullable Optional<T> value) throws IOException {
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