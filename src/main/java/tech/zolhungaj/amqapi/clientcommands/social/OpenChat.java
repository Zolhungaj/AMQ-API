package tech.zolhungaj.amqapi.clientcommands.social;

import tech.zolhungaj.amqapi.clientcommands.CommandName;

@CommandName("opened chat")
public record OpenChat(String target) implements SocialCommand{}
