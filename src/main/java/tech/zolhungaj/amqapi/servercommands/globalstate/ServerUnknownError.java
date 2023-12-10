package tech.zolhungaj.amqapi.servercommands.globalstate;

import tech.zolhungaj.amqapi.servercommands.Command;
import tech.zolhungaj.amqapi.servercommands.CommandTypeOld;

public class ServerUnknownError implements Command {
    public String commandName() {
        return CommandTypeOld.UNKNOWN_ERROR.commandName;
    }
}
