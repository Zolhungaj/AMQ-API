package tech.zolhungaj.amqapi.clientcommands.social;

import tech.zolhungaj.amqapi.clientcommands.CommandName;

@CommandName("player profile show badge")
public record SetProfileShowcaseBadge(int badgeId, int slotNumber) implements SocialCommand{}
