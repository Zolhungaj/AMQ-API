package tech.zolhungaj.amqapi.servercommands;

public record OnlinePlayerCountChange(
        Integer count
) implements Command{


    @Override
    public String getCommandName() {
        return CommandType.ONLINE_PLAYERS.commandName;
    }
}
