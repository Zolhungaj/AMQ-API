package tech.zolhungaj.amqapi.clientcommands.social;

import tech.zolhungaj.amqapi.clientcommands.CommandName;

@CommandName("player profile set chat badge")
public record SetProfileChatBadge(int badgeId) implements SocialCommand{}
