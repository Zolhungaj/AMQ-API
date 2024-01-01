package tech.zolhungaj.amqapi.servercommands.gameroom.lobby;

import tech.zolhungaj.amqapi.servercommands.CommandType;
import tech.zolhungaj.amqapi.servercommands.objects.PlayerIdentifier;

@CommandType("Player Changed To Spectator")
public record PlayerChangedToSpectator(
        PlayerIdentifier spectatorDescription,
        PlayerIdentifier playerDescription,
        Boolean isHost
){}
