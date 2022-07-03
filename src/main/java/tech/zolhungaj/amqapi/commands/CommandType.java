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
    FORCED_LOGOFF("force logoff");

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
