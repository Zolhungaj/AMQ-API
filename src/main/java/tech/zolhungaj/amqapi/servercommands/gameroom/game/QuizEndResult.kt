package tech.zolhungaj.amqapi.servercommands.gameroom.game

import tech.zolhungaj.amqapi.servercommands.CommandType
import tech.zolhungaj.amqapi.servercommands.objects.PlayerEndResult
import tech.zolhungaj.amqapi.servercommands.objects.ProgressBarState

@JvmRecord
@CommandType("quiz end result")
data class QuizEndResult(
    val resultStates: List<PlayerEndResult>,
    val progressBarState: ProgressBarState
)
