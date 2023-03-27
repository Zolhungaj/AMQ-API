package tech.zolhungaj.amqapi.servercommands.social;

import tech.zolhungaj.amqapi.servercommands.Command;
import tech.zolhungaj.amqapi.servercommands.CommandType;

public record GameInvite(
        int gameId,
        String sender
)implements Command {

    @Override
    public String commandName() {
        return CommandType.GAME_INVITE.commandName;
    }
}
