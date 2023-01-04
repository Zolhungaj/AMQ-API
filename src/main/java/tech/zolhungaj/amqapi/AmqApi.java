package tech.zolhungaj.amqapi;


import com.squareup.moshi.*;
import io.micrometer.core.lang.Nullable;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import tech.zolhungaj.amqapi.client.Client;
import tech.zolhungaj.amqapi.clientcommands.ClientCommand;
import tech.zolhungaj.amqapi.clientcommands.EmptyClientCommand;
import tech.zolhungaj.amqapi.servercommands.*;
import tech.zolhungaj.amqapi.servercommands.expandlibrary.ExpandLibraryEntryList;
import tech.zolhungaj.amqapi.servercommands.expandlibrary.ExpandLibraryEntryUpdated;
import tech.zolhungaj.amqapi.servercommands.gameroom.*;
import tech.zolhungaj.amqapi.servercommands.gameroom.lobby.PlayerChangedToSpectator;
import tech.zolhungaj.amqapi.servercommands.gameroom.lobby.SpectatorChangedToPlayer;
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
import java.util.concurrent.TimeoutException;

@Slf4j
public class AmqApi implements Runnable{
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
        log.info("{}, {}, {}", command, command.getClass(), command.getCommandName());

        onList
                .forEach(c -> c.call(command));
        onceList.removeIf(eventHandler -> eventHandler.call(command));
    }

    private Command serverCommandToCommand(Client.ServerCommand serverCommand) throws IOException {
        String commandName = serverCommand.command();
        JSONObject data = serverCommand.data();
        CommandType commandType = CommandType.forName(commandName);
        log.info("""
                ServerCommand: {}
                data: {}
                commandType: {}
                """, serverCommand, data, commandType);
        if(commandType == null){
            log.info("""
                    Unknown command:
                        command: {}
                        data: {}
                    """, commandName, data);
            return null;
        }
        String dataAsString = data.toString();
        try{
            Class<? extends Command> clazz = switch(commandType){
                case CHAT_MESSAGES -> GameChatUpdate.class;
                case GAME_INVITE -> GameInvite.class;
                case ONLINE_PLAYERS -> OnlinePlayerCountChange.class;
                case LOGIN_COMPLETE -> LoginComplete.class;
                case RANKED_STATE_CHANGE -> RankedGameStateChanged.class;
                case RANKED_LEADERBOARD_UPDATE -> RankedLeaderboardUpdate.class;
                case FRIEND_SOCIAL_STATUS_UPDATE -> FriendSocialStatusUpdate.class;
                case DIRECT_MESSAGE -> DirectMessage.class;
                case DIRECT_MESSAGE_RESPONSE -> DirectMessageResponse.class;
                case FORCED_LOGOFF -> ForcedLogoff.class;
                case EXPAND_LIBRARY_ENTRIES -> ExpandLibraryEntryList.class;
                case EXPAND_LIBRARY_UPDATE -> ExpandLibraryEntryUpdated.class;
                case NEW_FRIEND -> FriendAdded.class;
                case REMOVED_FRIEND -> FriendRemoved.class;
                case FRIEND_STATE_UPDATE -> FriendOnlineChange.class;
                case FRIEND_REQUEST_ACKNOWLEDGEMENT -> FriendRequestResponse.class;
                case FRIEND_NAME_UPDATE -> FriendNameChange.class;
                case FRIEND_PROFILE_IMAGE_UPDATE -> FriendProfileImageChange.class;
                case FRIEND_REQUEST -> FriendRequestReceived.class;
                case SINGLE_CHAT_MESSAGE -> GameChatMessage.class;
                case SYSTEM_CHAT_MESSAGE -> GameChatSystemMessage.class;
                case NEW_SPECTATOR -> SpectatorJoined.class;
                case SPECTATOR_LEFT -> SpectatorLeft.class;
                case PLAYER_CHANGED_TO_SPECTATOR -> PlayerChangedToSpectator.class;
                case SPECTATOR_CHANGED_TO_PLAYER -> SpectatorChangedToPlayer.class;
                case PLAYER_LEFT -> PlayerLeft.class;
                case POPOUT_MESSAGE -> PopoutMessage.class;
                case RANKED_CHAMPIONS_UPDATED -> RankedChampionsUpdate.class;
                case NEW_DONATION -> NewDonation.class;
                case ALL_ONLINE_USERS -> AllOnlineUsers.class;
                case ONLINE_USER_CHANGE -> OnlineUserChange.class;
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
                        PLAYER_REJOIN,
                        PLAYER_NAME_CHANGE,
                        JOIN_GAME,
                        ALERT,
                        HTML_ALERT,
                        SELF_NAME_UPDATE,
                        UNKNOWN_ERROR,
                        SERVER_RESTART,
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
                        -> throw new IllegalArgumentException();
            };
            return MOSHI.adapter(clazz).fromJson(dataAsString);
        } catch (IllegalArgumentException e){
            var path = Path.of("UNIMPLEMENTED-" +serverCommand.command().replace(" ", "-").concat(".json"));
            Files.writeString(path, "\n\n", StandardOpenOption.APPEND, StandardOpenOption.CREATE);
            Files.writeString(path, data.toString(4), StandardOpenOption.APPEND);
            return new NotImplementedCommand(commandType.commandName);
        } catch(JsonDataException e){
            var path = Path.of("ERROR-" + serverCommand.command().replace(" ", "-").concat(".json"));
            log.error("Something is wrong with the input data, writing to {} for inspection", path, e);
            Files.writeString(path, "\n\n", StandardOpenOption.APPEND, StandardOpenOption.CREATE);
            Files.writeString(path, data.toString(4), StandardOpenOption.APPEND);
            return new NotImplementedCommand(commandType.commandName);
        }
    }


    @Override
    public void run() {
        try(var newClient = new Client(username, password)){
            newClient.start(this.forceConnect);
            this.client = newClient;
            while(!Thread.interrupted()){
                Client.ServerCommand serverCommand = client.pollCommand(Duration.ofMinutes(5));
                Command command = serverCommandToCommand(serverCommand);
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