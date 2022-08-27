package tech.zolhungaj.amqapi.servercommands.globalstate;

import tech.zolhungaj.amqapi.servercommands.Command;
import tech.zolhungaj.amqapi.servercommands.CommandType;

public record ForcedLogoff(
        String reason
)implements Command {

    @Override
    public String getCommandName() {
        return CommandType.FORCED_LOGOFF.commandName;
    }
}
