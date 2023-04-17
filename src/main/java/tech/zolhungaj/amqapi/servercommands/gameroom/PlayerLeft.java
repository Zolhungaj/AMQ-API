package tech.zolhungaj.amqapi.servercommands.gameroom;

import tech.zolhungaj.amqapi.servercommands.Command;
import tech.zolhungaj.amqapi.servercommands.CommandType;
import tech.zolhungaj.amqapi.servercommands.objects.PlayerIdentifier;

import java.util.Optional;

public record PlayerLeft(
        Boolean kicked,
        Boolean disconnect,
        Optional<String> newHost,
        PlayerIdentifier player
) implements Command {
    public PlayerLeft{
        if(kicked == null) kicked = false;
        if(disconnect == null) disconnect = false;
        if(newHost == null) newHost = Optional.empty();
    }
    @Override
    public String commandName() {
        return CommandType.PLAYER_LEFT.commandName;
    }
}
