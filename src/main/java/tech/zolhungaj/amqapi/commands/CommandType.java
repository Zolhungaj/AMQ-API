package tech.zolhungaj.amqapi.commands;

import java.util.HashMap;
import java.util.Map;

public enum CommandType {

    CHAT_MESSAGES("game chat update"),
    ONLINE_PLAYERS("online player count change");

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
