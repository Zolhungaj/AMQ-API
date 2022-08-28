package tech.zolhungaj.amqapi.servercommands.gameroom.lobby;

import tech.zolhungaj.amqapi.servercommands.Command;
import tech.zolhungaj.amqapi.servercommands.CommandType;
import tech.zolhungaj.amqapi.servercommands.objects.Player;


public final class SpectatorChangedToPlayer extends Player implements Command {
    @Override
    public String getCommandName() {
        return CommandType.SPECTATOR_CHANGED_TO_PLAYER.commandName;
    }
}
