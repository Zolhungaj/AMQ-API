package tech.zolhungaj.amqapi.servercommands.globalstate;

import tech.zolhungaj.amqapi.servercommands.Command;
import tech.zolhungaj.amqapi.servercommands.CommandType;

public record OnlinePlayerCountChange(
        Integer count
) implements Command {


    @Override
    public String getCommandName() {
        return CommandType.ONLINE_PLAYERS.commandName;
    }
}
