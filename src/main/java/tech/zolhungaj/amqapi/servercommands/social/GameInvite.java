package tech.zolhungaj.amqapi.servercommands.social;

import tech.zolhungaj.amqapi.servercommands.Command;
import tech.zolhungaj.amqapi.servercommands.CommandTypeOld;

public record GameInvite(
        int gameId,
        String sender
)implements Command {

    @Override
    public String commandName() {
        return CommandTypeOld.GAME_INVITE.commandName;
    }
}
