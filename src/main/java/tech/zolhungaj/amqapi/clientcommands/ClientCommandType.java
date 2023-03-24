package tech.zolhungaj.amqapi.clientcommands;

public enum ClientCommandType {

    SEND_FRIEND_REQUEST("social", "friend request"),
    REMOVE_FRIEND("social", "remove friend"),
    OPEN_DIRECT_MESSAGE("social", "opened chat"),
    CLOSE_DIRECT_MESSAGE("social", "closed chat"),
    INVITE("social", "invite to game"),
    BLOCK("social", "block player"),
    UNBLOCK("social", "unblock player"),
    REPORT("social", "report player"),
    ;


    public final String commandType;
    public final String commandName;

    ClientCommandType(String commandType, String commandName) {
        this.commandType = commandType;
        this.commandName = commandName;
    }
}

