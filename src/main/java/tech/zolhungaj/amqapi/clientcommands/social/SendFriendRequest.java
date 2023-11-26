package tech.zolhungaj.amqapi.clientcommands.social;

import tech.zolhungaj.amqapi.clientcommands.CommandName;

@CommandName("friend request")
public record SendFriendRequest(String target) implements SocialCommand{}
