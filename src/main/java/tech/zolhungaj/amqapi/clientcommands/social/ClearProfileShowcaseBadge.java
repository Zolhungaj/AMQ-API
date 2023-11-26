package tech.zolhungaj.amqapi.clientcommands.social;

import tech.zolhungaj.amqapi.clientcommands.CommandName;

@CommandName("player profile clear badge")
public record ClearProfileShowcaseBadge(int slotNumber) implements SocialCommand{}
