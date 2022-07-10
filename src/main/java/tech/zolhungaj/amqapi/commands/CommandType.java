package tech.zolhungaj.amqapi.commands;

import java.util.HashMap;
import java.util.Map;

public enum CommandType {

    CHAT_MESSAGES("game chat update"),
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


    PLAYER_LEFT("Player Left"),
    PLAYER_REJOIN("Rejoining Player"),
    PLAYER_NAME_CHANGE("player name change"),


    QUIZ_NO_PLAYERS_AUTO_CLOSE("quiz no players"),

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
