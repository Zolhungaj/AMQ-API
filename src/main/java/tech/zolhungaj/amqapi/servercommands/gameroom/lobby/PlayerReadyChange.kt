package tech.zolhungaj.amqapi.servercommands.gameroom.lobby;

import tech.zolhungaj.amqapi.servercommands.CommandType;

@CommandType("Player Ready Change")
public record PlayerReadyChange(
        Boolean ready,
        int gamePlayerId
){}
