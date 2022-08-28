package tech.zolhungaj.amqapi.servercommands.gameroom.lobby;

import tech.zolhungaj.amqapi.servercommands.Command;
import tech.zolhungaj.amqapi.servercommands.CommandType;
import tech.zolhungaj.amqapi.servercommands.objects.PlayerIdentifier;

public record PlayerChangedToSpectator(
        PlayerIdentifier spectatorDescription,
        PlayerIdentifier playerDescription,
        Boolean isHost
) implements Command {
    @Override
    public String getCommandName() {
        return CommandType.PLAYER_CHANGED_TO_SPECTATOR.commandName;
    }
}
