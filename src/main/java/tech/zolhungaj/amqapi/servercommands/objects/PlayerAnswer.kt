package tech.zolhungaj.amqapi.servercommands.objects;

public record PlayerAnswer(
        String answer,
        AvatarPose pose,
        int gamePlayerId
) {

}
