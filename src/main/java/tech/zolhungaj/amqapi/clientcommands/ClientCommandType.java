package tech.zolhungaj.amqapi.clientcommands;

public enum ClientCommandType {

    SUBMIT_TO_EXPAND_LIBRARY("library", "expandLibrary answer"),
    OPEN_EXPAND_LIBRARY("library", "expandLibrary questions"),
    CLOSE_EXPAND_LIBRARY("library", "expandLibrary closed"),
    STOP_TRACKING_ONLINE_USERS("social", "stop tracking online users"),
    START_TRACKING_ONLINE_USERS("social", "get online users"),
    FRIEND_REQUEST_RESPONSE("social", "friend request response"),
    SEND_CHAT_MESSAGE("lobby", "game chat message"),


    ;


    public final String commandType;
    public final String commandName;

    ClientCommandType(String commandType, String commandName) {
        this.commandType = commandType;
        this.commandName = commandName;
    }
}

