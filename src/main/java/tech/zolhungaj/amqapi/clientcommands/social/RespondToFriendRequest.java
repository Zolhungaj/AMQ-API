package tech.zolhungaj.amqapi.clientcommands.social;

import tech.zolhungaj.amqapi.clientcommands.CommandName;

@CommandName("friend request response")
public record RespondToFriendRequest(
        String target,
        boolean accept
) implements SocialCommand {}
