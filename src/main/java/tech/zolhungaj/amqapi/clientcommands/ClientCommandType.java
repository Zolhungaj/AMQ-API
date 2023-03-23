package tech.zolhungaj.amqapi.clientcommands;

public enum ClientCommandType {

    SUBMIT_TO_EXPAND_LIBRARY("library", "expandLibrary answer"),
    OPEN_EXPAND_LIBRARY("library", "expandLibrary questions"),
    CLOSE_EXPAND_LIBRARY("library", "expandLibrary closed"),
    STOP_TRACKING_ONLINE_USERS("social", "stop tracking online users"),
    START_TRACKING_ONLINE_USERS("social", "get online users"),
    RESPOND_TO_FRIEND_REQUEST("social", "friend request response"),
    SEND_FRIEND_REQUEST("social", "friend request"),
    REMOVE_FRIEND("social", "remove friend"),
    DIRECT_MESSAGE("social", "chat message"),
    OPEN_DIRECT_MESSAGE("social", "opened chat"),
    CLOSE_DIRECT_MESSAGE("social", "closed chat"),
    INVITE("social", "invite to game"),
    BLOCK("social", "block player"),
    UNBLOCK("social", "unblock player"),
    REPORT("social", "report player"),
    SEND_CHAT_MESSAGE("lobby", "game chat message"),
    KICK_PLAYER("lobby", "kick player"),
    HOST_GAME("roombrowser", "host room"),
    HOST_SOLO_GAME("roombrowser", "host solo room"),


    ;


    public final String commandType;
    public final String commandName;

    ClientCommandType(String commandType, String commandName) {
        this.commandType = commandType;
        this.commandName = commandName;
    }
}

