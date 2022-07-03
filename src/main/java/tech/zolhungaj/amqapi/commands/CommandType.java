package tech.zolhungaj.amqapi.commands;

import java.util.HashMap;
import java.util.Map;

public enum CommandType {

    CHAT_MESSAGES("game chat update"),
    GAME_INVITE("game invite"),
    LOGIN_COMPLETE("login complete"),
    ONLINE_PLAYERS("online player count change"),
    RANKED_STATE_CHANGE("ranked game state change");

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
