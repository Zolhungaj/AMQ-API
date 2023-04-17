package tech.zolhungaj.amqapi.servercommands.globalstate;

import tech.zolhungaj.amqapi.servercommands.Command;
import tech.zolhungaj.amqapi.servercommands.CommandType;

public class ServerUnknownError implements Command {
    public String commandName() {
        return CommandType.UNKNOWN_ERROR.commandName;
    }
}
