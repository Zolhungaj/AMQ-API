package tech.zolhungaj.amqapi.servercommands.gameroom.game

import tech.zolhungaj.amqapi.servercommands.CommandType
import tech.zolhungaj.amqapi.servercommands.objects.PlayerAnswer
import tech.zolhungaj.amqapi.servercommands.objects.ProgressBarState

@JvmRecord
@CommandType("player answers")
data class AnswerReveal(
    val answers: List<PlayerAnswer>,
    val progressBarState: ProgressBarState?
)
