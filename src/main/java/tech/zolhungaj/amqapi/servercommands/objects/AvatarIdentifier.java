package tech.zolhungaj.amqapi.servercommands.objects;

public record AvatarIdentifier(
        Integer avatarId,
        Integer colorId,
        Boolean optionActive
){}
