package tech.zolhungaj.amqapi.clientcommands.social;

import tech.zolhungaj.amqapi.clientcommands.CommandName;

@CommandName("remove friend")
public record RemoveFriend(String target) implements SocialCommand{}
