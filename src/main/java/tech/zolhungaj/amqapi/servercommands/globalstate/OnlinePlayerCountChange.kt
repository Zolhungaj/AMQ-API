package tech.zolhungaj.amqapi.servercommands.globalstate;

import tech.zolhungaj.amqapi.servercommands.CommandType;

@CommandType("online player count change")
public record OnlinePlayerCountChange(
        int count
){}
