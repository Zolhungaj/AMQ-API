package tech.zolhungaj.amqapi.clientcommands;

public enum ClientCommandType {

    EXPAND_LIBRARY_SUBMIT("library", "expandLibrary answer"),
    EXPAND_LIBRARY_OPEN("library", "expandLibrary questions"),
    EXPAND_LIBRARY_CLOSE("library", "expandLibrary closed")


    ;

    public final String commandType;
    public final String commandName;

    ClientCommandType(String commandType, String commandName) {
        this.commandType = commandType;
        this.commandName = commandName;
    }
}

