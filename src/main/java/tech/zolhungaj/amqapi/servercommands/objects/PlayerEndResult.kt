package tech.zolhungaj.amqapi.servercommands.objects;

public record PlayerEndResult(
        AvatarPose pose,
        int endPosition,
        int gamePlayerId
) {
}
