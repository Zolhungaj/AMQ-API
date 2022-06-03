package tech.zolhungaj.amqapi.commands;

public enum Commands {

    CHAT_MESSAGES("game chat update"),
    ONLINE_PLAYERS("online player count change");

    public final String commandName;
    Commands(String commandName){
        this.commandName = commandName;
    }
}
