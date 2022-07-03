package tech.zolhungaj.amqapi.commands;

public record OnlinePlayerCountChange(
        Integer count
) implements Command{


    @Override
    public String getCommandName() {
        return CommandType.ONLINE_PLAYERS.commandName;
    }
}
