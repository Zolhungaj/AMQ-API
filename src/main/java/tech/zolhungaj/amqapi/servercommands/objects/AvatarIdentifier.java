package tech.zolhungaj.amqapi.servercommands.objects;

public record AvatarIdentifier(
        int avatarId,
        int colorId,
        Boolean optionActive
){}
