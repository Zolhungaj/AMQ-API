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
import tech.zolhungaj.amqapi.servercommands.expandlibrary.ExpandLibraryEntryList;
import tech.zolhungaj.amqapi.servercommands.expandlibrary.ExpandLibraryEntryUpdated;
import tech.zolhungaj.amqapi.servercommands.gameroom.*;
import tech.zolhungaj.amqapi.servercommands.gameroom.game.*;
import tech.zolhungaj.amqapi.servercommands.gameroom.lobby.*;
import tech.zolhungaj.amqapi.servercommands.globalstate.*;
import tech.zolhungaj.amqapi.servercommands.objects.AvatarPose;
import tech.zolhungaj.amqapi.servercommands.objects.ListStatus;
import tech.zolhungaj.amqapi.servercommands.objects.PlayerStatus;
import tech.zolhungaj.amqapi.servercommands.objects.SongType;
import tech.zolhungaj.amqapi.servercommands.social.*;
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
            .add(ExpandLibraryEntryList.ExpandSongStatus.class, IntegerEnumJsonAdapter.create(ExpandLibraryEntryList.ExpandSongStatus.class).withUnknownFallback(ExpandLibraryEntryList.ExpandSongStatus.UNKNOWN))
            .add(SongType.class, IntegerEnumJsonAdapter.create(SongType.class).withUnknownFallback(SongType.UNKNOWN))
            .add(AnimeList.class, IntegerEnumJsonAdapter.create(AnimeList.class))
            .add(AvatarPose.class, IntegerEnumJsonAdapter.create(AvatarPose.class))
            .add(ListStatus.class, IntegerEnumJsonAdapter.create(ListStatus.class))
            .add(LoginComplete.QuestDescription.QuestStateWeekSlot.class, new LoginComplete.QuestDescription.QuestStateWeekSlotAdapter())
            .build();
    private final Map<Class<? extends Command>, List<Consumer<? extends Command>>> onMap = new HashMap<>();
    private final Map<Class<? extends Command>, List<Predicate<? extends Command>>> onceMap = new HashMap<>();
    private final Map<String, List<Consumer<JSONObject>>> interceptJsonMap = new HashMap<>();
    private final List<Consumer<Command>> interceptCommandList = new ArrayList<>();
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

    public <T extends Command> void on(Class<T> clazz, Consumer<T> consumer){
        this.onMap.putIfAbsent(clazz, new ArrayList<>());
        this.onMap.get(clazz).add(consumer);
    }

    public <T extends Command> void once(Class<T> clazz, Predicate<T> predicate){
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

    public void onAllCommands(Consumer<Command> consumer){
        this.interceptCommandList.add(consumer);
    }

    /** Trigger every EventHandler with the provided Command.
     * Usage from outside this class is primarily intended to be for testing/debugging purposes.
     *
     * @param command the Command to be handled
     */
    @SuppressWarnings("unchecked")
    public void handle(Command command){
        log.info("{}, {}, {}", command, command.getClass(), command.commandName());
        interceptCommandList.forEach(c -> c.accept(command));
        if(onMap.containsKey(command.getClass())){
            List<Consumer<? extends Command>> consumers = onMap.get(command.getClass());
            for(Consumer<? extends Command> consumer : consumers){
                Consumer<Command> wayBetterConsumer = (Consumer<Command>) consumer;
                wayBetterConsumer.accept(command);
            }
        }
        if(onceMap.containsKey(command.getClass())){
            List<Predicate<? extends Command>> predicates = onceMap.get(command.getClass());
            Iterator<Predicate<? extends Command>> iterator = predicates.iterator();
            while(iterator.hasNext()){
                Predicate<? extends Command> predicate = iterator.next();
                Predicate<Command> wayBetterPredicate = (Predicate<Command>) predicate;
                boolean result = wayBetterPredicate.test(command);
                if(result){
                    iterator.remove();
                }
            }
        }
    }

    private Command serverCommandToCommand(Client.ServerCommand serverCommand) throws IOException {
        String commandName = serverCommand.command();
        JSONObject data = serverCommand.data();
        if(interceptJsonMap.containsKey(commandName)){
            interceptJsonMap.get(commandName).forEach(consumer -> consumer.accept(data));
        }
        interceptJsonList.forEach(consumer -> consumer.accept(data));

        CommandType commandType = CommandType.forName(commandName);
        log.info("""
                ServerCommand: {}
                data: {}
                commandType: {}
                """, serverCommand, data, commandType);
        if(commandType == null){
            return new NotStartedCommand(commandName, data);
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
                case FILE_SERVER_STATE_CHANGE -> FileServerStatus.class;
                case SERVER_RESTART -> ServerRestartWarning.class;
                case NEW_QUEST_EVENTS -> NewQuestEvents.class;
                case PLAYER_READY_CHANGE -> PlayerReadyChange.class;
                case NEW_PLAYER -> NewPlayer.class;
                case AVATAR_DRIVE_UPDATE -> AvatarDriveUpdate.class;
                case HOST_GAME -> GameHosted.class;
                case PLAYER_PROFILE -> PlayerProfile.class;
                case AVATAR_CHANGE -> PlayerChangedAvatar.class;
                case ALERT -> Alert.class;
                case PLAYER_REJOIN -> PlayerRejoin.class;
                case HTML_ALERT -> HtmlAlert.class;
                case SELF_NAME_UPDATE -> SelfNameChange.class;
                case UNKNOWN_ERROR -> ServerUnknownError.class;
                case RANKED_SCORE_UPDATE -> RankedScoreUpdate.class;
                case TICKET_ROLL_RESULT -> TicketRollResult.class;
                case GAME_STARTING -> GameStarting.class;
                case QUIZ_READY -> QuizReady.class;
                case QUIZ_NEXT_VIDEO_INFO -> NextVideoInfo.class;
                case QUIZ_WAITING_BUFFERING -> WaitingForBuffering.class;
                case PLAY_NEXT_SONG -> PlayNextSong.class;
                case QUIZ_SKIP_MESSAGE -> QuizSkipMessage.class;
                case GUESS_PHASE_OVER -> GuessPhaseOver.class;
                case PLAYER_ANSWERS -> AnswerReveal.class;
                case ANSWER_RESULTS -> AnswerResults.class;
                case SEND_FEEDBACK -> SongFeedbackRequest.class;
                case PLAYER_ANSWERED -> PlayersAnswered.class;
                case QUIZ_END_RESULT -> QuizEndResult.class;
                case QUIZ_OVER -> QuizOver.class;
                case QUIZ_NO_SONGS -> QuizNoSongs.class;
                case QUIZ_FATAL_ERROR -> QuizFatalError.class;
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
                        PLAYER_NAME_CHANGE,
                        JOIN_GAME,
                        SAVED_QUIZ_SETTINGS_DELETED,
                        SAVE_QUIZ_SETTINGS,
                        USE_AVATAR_RESPONSE,
                        UNLOCK_AVATAR,
                        LOCK_AVATAR,
                        UNLOCK_EMOTE,
                        LOCK_EMOTE,
                        ADD_FAVOURITE_AVATAR_RESPONSE,
                        DELETE_FAVOURITE_AVATAR_RESPONSE,
                        TICKET_ROLL_ERROR,
                        AVATAR_DRIVE_LEADERBOARD,
                        PATREON_UPDATE,
                        FREE_AVATAR_DONATION_RESPONSE,
                        QUIZ_NO_PLAYERS_AUTO_CLOSE,
                        UPDATE_MAL_LAST_UPDATE,
                        UPDATE_ANILIST_LAST_UPDATE,
                        UPDATE_KITSU_LAST_UPDATE,
                        ANIME_LIST_UPDATE_RESPONSE,
                        NICKNAME_AVAILABILITY_RESPONSE,
                        CHANGE_NICKNAME_RESPONSE,
                        DIRECT_MESSAGE_ALERT,
                        SERVER_MESSAGE,
                        PLAYER_ONLINE_UPDATE
                        -> throw new IllegalArgumentException();
            };
            return MOSHI.adapter(clazz).fromJson(dataAsString);
        } catch (IllegalArgumentException e){
            log.error("",e);
            return new NotImplementedCommand(commandName, data);
        } catch(JsonDataException e){
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

