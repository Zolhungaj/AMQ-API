package tech.zolhungaj.amqapi.servercommands.gameroom;

import tech.zolhungaj.amqapi.servercommands.Command;
import tech.zolhungaj.amqapi.servercommands.CommandType;
import tech.zolhungaj.amqapi.servercommands.objects.PlayerIdentifier;

import java.util.Optional;

public record PlayerLeft(
        Optional<Boolean> kicked,
        Optional<Boolean> disconnect,
        Optional<String> newHost,
        PlayerIdentifier player
) implements Command {
    @Override
    public String commandName() {
        return CommandType.PLAYER_LEFT.commandName;
    }
}
