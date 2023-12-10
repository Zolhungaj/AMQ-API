package tech.zolhungaj.amqapi.servercommands.gameroom.lobby;

import tech.zolhungaj.amqapi.servercommands.Command;
import tech.zolhungaj.amqapi.servercommands.CommandTypeOld;
import tech.zolhungaj.amqapi.servercommands.objects.PlayerIdentifier;

public record PlayerChangedToSpectator(
        PlayerIdentifier spectatorDescription,
        PlayerIdentifier playerDescription,
        Boolean isHost
) implements Command {
    @Override
    public String commandName() {
        return CommandTypeOld.PLAYER_CHANGED_TO_SPECTATOR.commandName;
    }
}
