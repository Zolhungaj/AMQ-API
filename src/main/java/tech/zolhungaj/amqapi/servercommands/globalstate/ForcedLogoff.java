package tech.zolhungaj.amqapi.servercommands.globalstate;

import tech.zolhungaj.amqapi.servercommands.CommandType;

@CommandType("force logoff")
public record ForcedLogoff(
        String reason
){}
