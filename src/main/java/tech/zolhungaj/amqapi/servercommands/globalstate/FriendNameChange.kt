package tech.zolhungaj.amqapi.servercommands.globalstate;

import tech.zolhungaj.amqapi.servercommands.CommandType;

@CommandType("friend name change")
public record FriendNameChange(
        String newName,
        String oldName
){}
