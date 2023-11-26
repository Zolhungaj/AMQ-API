package tech.zolhungaj.amqapi.clientcommands.social;

import tech.zolhungaj.amqapi.clientcommands.CommandName;

@CommandName("chat message")
public record SendDirectMessage(String target, String message) implements SocialCommand {}
