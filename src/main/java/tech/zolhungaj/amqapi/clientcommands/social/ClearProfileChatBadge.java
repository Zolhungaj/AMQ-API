package tech.zolhungaj.amqapi.clientcommands.social;

import tech.zolhungaj.amqapi.clientcommands.CommandName;

@CommandName("player profile clear chat badge")
public record ClearProfileChatBadge(int badgeId) implements SocialCommand{}
