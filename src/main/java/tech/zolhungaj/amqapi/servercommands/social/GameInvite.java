package tech.zolhungaj.amqapi.servercommands.social;

import tech.zolhungaj.amqapi.servercommands.Command;
import tech.zolhungaj.amqapi.servercommands.CommandType;

public record GameInvite(
        Integer gameId,
        String sender
)implements Command {

    @Override
    public String getCommandName() {
        return CommandType.GAME_INVITE.commandName;
    }
}
