package tech.zolhungaj.amqapi.servercommands.globalstate;

import tech.zolhungaj.amqapi.servercommands.Command;
import tech.zolhungaj.amqapi.servercommands.CommandTypeOld;

public record OnlinePlayerCountChange(
        int count
) implements Command {


    @Override
    public String commandName() {
        return CommandTypeOld.ONLINE_PLAYERS.commandName;
    }
}
