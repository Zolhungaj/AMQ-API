package tech.zolhungaj.amqapi.servercommands.gameroom.game;

import tech.zolhungaj.amqapi.servercommands.CommandType;
import tech.zolhungaj.amqapi.servercommands.objects.PlayerEndResult;
import tech.zolhungaj.amqapi.servercommands.objects.ProgressBarState;

import java.util.List;

@CommandType("quiz end result")
public record QuizEndResult(
        List<PlayerEndResult> resultStates,
        ProgressBarState progressBarState
){}
