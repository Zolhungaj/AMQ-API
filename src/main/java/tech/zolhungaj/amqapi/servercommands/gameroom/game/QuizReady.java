package tech.zolhungaj.amqapi.servercommands.gameroom.game;

import tech.zolhungaj.amqapi.servercommands.CommandType;

@CommandType("quiz ready")
public record QuizReady(
        int numberOfSongs
){}
