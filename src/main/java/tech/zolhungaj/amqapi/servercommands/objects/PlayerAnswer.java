package tech.zolhungaj.amqapi.servercommands.objects;

public record PlayerAnswer(
        String answer,
        int pose,
        int gamePlayerId
) {

}
