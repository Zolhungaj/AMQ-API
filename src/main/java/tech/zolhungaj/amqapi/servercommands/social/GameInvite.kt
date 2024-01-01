package tech.zolhungaj.amqapi.servercommands.social;

import tech.zolhungaj.amqapi.servercommands.CommandType;

@CommandType("game invite")
public record GameInvite(
        int gameId,
        String sender
){}
