package tech.zolhungaj.amqapi.servercommands.gameroom.game;

import tech.zolhungaj.amqapi.servercommands.CommandType;
import tech.zolhungaj.amqapi.servercommands.objects.PlayerAnswer;
import tech.zolhungaj.amqapi.servercommands.objects.ProgressBarState;

import java.util.List;
import java.util.Optional;

@CommandType("player answers")
public record AnswerReveal(
    List<PlayerAnswer> answers,
    Optional<ProgressBarState> progressBarState
){}
