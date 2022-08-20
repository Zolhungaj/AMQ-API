package tech.zolhungaj.amqapi.servercommands;

public class NotImplementedCommand implements Command{

    private final String commandName;

    public NotImplementedCommand(String commandName){
        this.commandName = commandName;
    }

    @Override
    public String getCommandName() {
        return commandName;
    }
}
