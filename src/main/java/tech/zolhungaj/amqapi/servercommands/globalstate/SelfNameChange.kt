package tech.zolhungaj.amqapi.servercommands.globalstate;

import tech.zolhungaj.amqapi.servercommands.CommandType;

@CommandType("self name changed")
public record SelfNameChange(String newName){}
