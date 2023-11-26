package tech.zolhungaj.amqapi.clientcommands.social;

import tech.zolhungaj.amqapi.clientcommands.CommandName;

@CommandName("unblock player")
public record Unblock(String target) implements SocialCommand{}
