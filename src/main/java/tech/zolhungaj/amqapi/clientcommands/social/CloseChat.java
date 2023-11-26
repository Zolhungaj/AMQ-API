package tech.zolhungaj.amqapi.clientcommands.social;

import tech.zolhungaj.amqapi.clientcommands.CommandName;

@CommandName("closed chat")
public record CloseChat(String target) implements SocialCommand{}
