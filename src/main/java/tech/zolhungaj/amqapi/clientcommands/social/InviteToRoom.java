package tech.zolhungaj.amqapi.clientcommands.social;

import tech.zolhungaj.amqapi.clientcommands.CommandName;

@CommandName("invite to game")
public record InviteToRoom(
        String target
) implements SocialCommand{}
