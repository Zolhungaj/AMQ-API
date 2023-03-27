package tech.zolhungaj.amqapi.servercommands.globalstate;

import tech.zolhungaj.amqapi.servercommands.Command;
import tech.zolhungaj.amqapi.servercommands.CommandType;

public record OnlinePlayerCountChange(
        int count
) implements Command {


    @Override
    public String commandName() {
        return CommandType.ONLINE_PLAYERS.commandName;
    }
}
