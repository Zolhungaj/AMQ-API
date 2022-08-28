package tech.zolhungaj.amqapi;


import com.squareup.moshi.*;
import io.micrometer.core.lang.Nullable;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tech.zolhungaj.amqapi.client.Client;
import tech.zolhungaj.amqapi.clientcommands.ClientCommand;
import tech.zolhungaj.amqapi.clientcommands.EmptyClientCommand;
import tech.zolhungaj.amqapi.servercommands.*;
import tech.zolhungaj.amqapi.servercommands.gameroom.GameChatMessage;
import tech.zolhungaj.amqapi.servercommands.gameroom.GameChatSystemMessage;
import tech.zolhungaj.amqapi.servercommands.gameroom.GameChatUpdate;
import tech.zolhungaj.amqapi.servercommands.globalstate.*;
import tech.zolhungaj.amqapi.servercommands.social.*;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
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

    private Client client;

    public AmqApi(String username, String password, boolean forceConnect) {
        this.username = username;
        this.password = password;
        this.forceConnect = forceConnect;
    }

    public void sendCommand(ClientCommand command){
        if(command instanceof EmptyClientCommand){
            this.client.sendCommand(command.type(), command.command(), null);
        }else{
            this.client.sendCommand(command.type(), command.command(), command);
        }
    }

    public void on(EventHandler event){
        this.onList.add(event);
    }
    public void once(EventHandler event){
        this.onceList.add(event);
    }

    private void handle(Command command){
        LOG.info("{}, {}, {}", command, command.getClass(), command.getCommandName());

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
        var path = Path.of(serverCommand.command().replace(" ", "-").concat(".json"));
        Files.writeString(path, "\n\n", StandardOpenOption.APPEND, StandardOpenOption.CREATE);
        Files.writeString(path, data.toString(4), StandardOpenOption.APPEND);
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
            case RANKED_STATE_CHANGE -> MOSHI.adapter(RankedGameStateChanged.class).fromJson(dataAsString);
            case RANKED_LEADERBOARD_UPDATE -> MOSHI.adapter(RankedLeaderboardUpdate.class).fromJson(dataAsString);
            case FRIEND_SOCIAL_STATUS_UPDATE -> MOSHI.adapter(FriendSocialStatusUpdate.class).fromJson(dataAsString);
            case DIRECT_MESSAGE -> MOSHI.adapter(DirectMessage.class).fromJson(dataAsString);
            case DIRECT_MESSAGE_RESPONSE -> MOSHI.adapter(DirectMessageResponse.class).fromJson(dataAsString);
            case FORCED_LOGOFF -> MOSHI.adapter(ForcedLogoff.class).fromJson(dataAsString);
            case EXPAND_LIBRARY_ENTRIES -> MOSHI.adapter(ExpandLibraryEntryList.class).fromJson(dataAsString);
            case NEW_FRIEND -> MOSHI.adapter(FriendAdded.class).fromJson(dataAsString);
            case REMOVED_FRIEND -> MOSHI.adapter(FriendRemoved.class).fromJson(dataAsString);
            case FRIEND_STATE_UPDATE -> MOSHI.adapter(FriendOnlineChange.class).fromJson(dataAsString);
            case FRIEND_REQUEST_ACKNOWLEDGEMENT -> MOSHI.adapter(FriendRequestResponse.class).fromJson(dataAsString);
            case FRIEND_NAME_UPDATE -> MOSHI.adapter(FriendNameChange.class).fromJson(dataAsString);
            case FRIEND_PROFILE_IMAGE_UPDATE -> MOSHI.adapter(FriendProfileImageChange.class).fromJson(dataAsString);
            case FRIEND_REQUEST -> MOSHI.adapter(FriendRequestReceived.class).fromJson(dataAsString);
            case SINGLE_CHAT_MESSAGE -> MOSHI.adapter(GameChatMessage.class).fromJson(dataAsString);
            case SYSTEM_CHAT_MESSAGE -> MOSHI.adapter(GameChatSystemMessage.class).fromJson(dataAsString);
            case //TODO: implement each of these
                    BATTLE_ROYALE_READY,
                    BATTLE_ROYALE_BEGIN,
                    BATTLE_ROYALE_SPAWN_OBJECT,
                    BATTLE_ROYALE_DELETE_OBJECT,
                    BATTLE_ROYALE_ADD_COLLECTED_NAME,
                    BATTLE_ROYALE_DELETE_COLLECTED_NAME,
                    BATTLE_ROYALE_CONTAINER_CONTENT,
                    BATTLE_ROYALE_CONTAINER_DELETE_ENTRY,
                    BATTLE_ROYALE_SPAWN_PLAYER,
                    BATTLE_ROYALE_UPDATE_PLAYER_POSITION,
                    BATTLE_ROYALE_DELETE_PLAYER,
                    BATTLE_ROYALE_PHASE_OVER,
                    BATTLE_ROYALE_FIX_POSITION,
                    BATTLE_ROYALE_TILE_COUNT,
                    BATTLE_ROYALE_TILE_UPDATE_SPECTATOR_COUNT,
                    BATTLE_ROYALE_RETURN_TO_MAP,
                    PLAYER_LEFT,
                    PLAYER_REJOIN,
                    PLAYER_NAME_CHANGE,
                    JOIN_GAME,
                    ALERT,
                    HTML_ALERT,
                    SELF_NAME_UPDATE,
                    UNKNOWN_ERROR,
                    SERVER_RESTART,
                    NEW_DONATION,
                    POPOUT_MESSAGE,
                    RANKED_SCORE_UPDATE,
                    PLAYER_PROFILE,
                    SAVED_QUIZ_SETTINGS_DELETED,
                    SAVE_QUIZ_SETTINGS,
                    USE_AVATAR_RESPONSE,
                    UNLOCK_AVATAR,
                    LOCK_AVATAR,
                    UNLOCK_EMOTE,
                    LOCK_EMOTE,
                    ADD_FAVOURITE_AVATAR_RESPONSE,
                    DELETE_FAVOURITE_AVATAR_RESPONSE,
                    TICKET_ROLL_RESULT,
                    TICKET_ROLL_ERROR,
                    AVATAR_DRIVE_UPDATE,
                    AVATAR_DRIVE_LEADERBOARD,
                    PATREON_UPDATE,
                    FREE_AVATAR_DONATION_RESPONSE,
                    QUIZ_NO_PLAYERS_AUTO_CLOSE,
                    UPDATE_MAL_LAST_UPDATE,
                    UPDATE_ANILIST_LAST_UPDATE,
                    UPDATE_KITSU_LAST_UPDATE,
                    ANIME_LIST_UPDATE_RESPONSE,
                    FILE_SERVER_STATE_CHANGE,
                    NICKNAME_AVAILABILITY_RESPONSE,
                    CHANGE_NICKNAME_RESPONSE,
                    DIRECT_MESSAGE_ALERT,
                    SERVER_MESSAGE,
                    PLAYER_ONLINE_UPDATE
                    -> new NotImplementedCommand(commandType.commandName);
        };
    }


    @Override
    public void run() {
        try(var newClient = new Client(username, password)){
            newClient.start(this.forceConnect);
            this.client = newClient;
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