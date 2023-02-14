package tech.zolhungaj.amqapi.servercommands;

import java.util.HashMap;
import java.util.Map;

public enum CommandType {

    CHAT_MESSAGES("game chat update"),
    SINGLE_CHAT_MESSAGE("Game Chat Message"),
    SYSTEM_CHAT_MESSAGE("Game System Chat Message"),
    NEW_SPECTATOR("New Spectator"),
    SPECTATOR_LEFT("Spectator Left"),
    PLAYER_LEFT("Player Left"),
    SPECTATOR_CHANGED_TO_PLAYER("Spectator Change To Player"),
    PLAYER_CHANGED_TO_SPECTATOR("Player Changed To Spectator"),
    GAME_INVITE("game invite"),
    LOGIN_COMPLETE("login complete"),
    ONLINE_PLAYERS("online player count change"),
    RANKED_STATE_CHANGE("ranked game state change"),
    RANKED_LEADERBOARD_UPDATE("ranked standing updated"),
    FRIEND_SOCIAL_STATUS_UPDATE("friend social status change"),
    DIRECT_MESSAGE("chat message"),
    DIRECT_MESSAGE_RESPONSE("chat message response"),
    FORCED_LOGOFF("force logoff"),
    BATTLE_ROYALE_READY("battle royal ready"),
    BATTLE_ROYALE_BEGIN("battle royal spawn"),
    BATTLE_ROYALE_SPAWN_OBJECT("battle royal object spawn"),
    BATTLE_ROYALE_DELETE_OBJECT("battle royal object despawn"),
    BATTLE_ROYALE_ADD_COLLECTED_NAME("new collected name entry"),
    BATTLE_ROYALE_DELETE_COLLECTED_NAME("drop name entry"),
    BATTLE_ROYALE_CONTAINER_CONTENT("battle royal data store content"),
    BATTLE_ROYALE_CONTAINER_DELETE_ENTRY("container entry despawn"),
    BATTLE_ROYALE_SPAWN_PLAYER("battle royal spawn player"),
    BATTLE_ROYALE_UPDATE_PLAYER_POSITION("battle royal new player position"),
    BATTLE_ROYALE_DELETE_PLAYER("battle royal despawn player"),
    BATTLE_ROYALE_PHASE_OVER("battle royal phase over"),
    BATTLE_ROYALE_FIX_POSITION("battle royale correct posistion"),
    BATTLE_ROYALE_TILE_COUNT("battle royal tile count"),
    BATTLE_ROYALE_TILE_UPDATE_SPECTATOR_COUNT("tile spectator count change"),
    BATTLE_ROYALE_RETURN_TO_MAP("battle royal return map"),
    PLAYER_REJOIN("Rejoining Player"),
    PLAYER_NAME_CHANGE("player name change"),

    JOIN_GAME("Join Game"),
    ALERT("alert"),
    HTML_ALERT("html alert"),
    SELF_NAME_UPDATE("self name changed"),
    UNKNOWN_ERROR("unknown error"),
    SERVER_RESTART("server restart"),
    NEW_DONATION("new donation"),
    POPOUT_MESSAGE("popout message"),
    RANKED_SCORE_UPDATE("ranked score update"),
    PLAYER_PROFILE("player profile"),
    SAVED_QUIZ_SETTINGS_DELETED("quiz setting deleted"),
    SAVE_QUIZ_SETTINGS("save quiz settings"),


    USE_AVATAR_RESPONSE("use avatar"),
    UNLOCK_AVATAR("unlock avatar"),
    LOCK_AVATAR("lock avatar"),
    UNLOCK_EMOTE("emote unlocked"),
    LOCK_EMOTE("emote locked"),
    ADD_FAVOURITE_AVATAR_RESPONSE("new favourite avatar"),
    DELETE_FAVOURITE_AVATAR_RESPONSE("remove favorite avatar"),

    TICKET_ROLL_RESULT("ticket roll result"),
    TICKET_ROLL_ERROR("ticket roll error"),

    AVATAR_DRIVE_UPDATE("avatar drive changes"),
    AVATAR_DRIVE_LEADERBOARD("avatar drive standings"),
    PATREON_UPDATE("patreon changed"),
    FREE_AVATAR_DONATION_RESPONSE("free avatar donation"),

    QUIZ_NO_PLAYERS_AUTO_CLOSE("quiz no players"),

    UPDATE_MAL_LAST_UPDATE("malLastUpdate update"),
    UPDATE_ANILIST_LAST_UPDATE("aniListLastUpdate update"),
    UPDATE_KITSU_LAST_UPDATE("kitsuLastUpdate update"),
    ANIME_LIST_UPDATE_RESPONSE("anime list update result"),

    FILE_SERVER_STATE_CHANGE("server state change"),

    NICKNAME_AVAILABILITY_RESPONSE("nickname available"),
    CHANGE_NICKNAME_RESPONSE("change nickname"),

    FRIEND_REQUEST("new friend request recived"),
    DIRECT_MESSAGE_ALERT("new chat alert"),
    SERVER_MESSAGE("server message"),

    PLAYER_ONLINE_UPDATE("player online change"),

    NEW_FRIEND("new friend"),
    REMOVED_FRIEND("friend removed"),
    FRIEND_STATE_UPDATE("friend state change"),
    FRIEND_NAME_UPDATE("friend name change"),
    FRIEND_PROFILE_IMAGE_UPDATE("friend profile image change"),
    FRIEND_REQUEST_ACKNOWLEDGEMENT("friend request"),

    EXPAND_LIBRARY_ENTRIES("expandLibrary questions"),
    EXPAND_LIBRARY_UPDATE("expandLibrary song answered"),

    RANKED_CHAMPIONS_UPDATED("ranked champions updated"),
    ALL_ONLINE_USERS("all online users"),
    ONLINE_USER_CHANGE("online user change"),
    NEW_QUEST_EVENTS("new quest events"),
    PLAYER_READY_CHANGE("Player Ready Change"),
    NEW_PLAYER("New Player"),
    ;
    public final String commandName;

    private static final Map<String, CommandType> map = new HashMap<>();
    static {
        for(CommandType c : CommandType.values()){
            map.put(c.commandName, c);
        }
    }
    public static CommandType forName(String commandName){
        return map.get(commandName);
    }
    CommandType(String commandName){
        this.commandName = commandName;
    }
}
