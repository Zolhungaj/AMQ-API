package tech.zolhungaj.amqapi.servercommands.globalstate;

import tech.zolhungaj.amqapi.servercommands.Command;
import tech.zolhungaj.amqapi.servercommands.CommandTypeOld;

public record ForcedLogoff(
        String reason
)implements Command {

    @Override
    public String commandName() {
        return CommandTypeOld.FORCED_LOGOFF.commandName;
    }
}
