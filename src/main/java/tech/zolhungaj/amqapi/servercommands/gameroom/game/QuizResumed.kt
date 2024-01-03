package tech.zolhungaj.amqapi.servercommands.gameroom.game

import tech.zolhungaj.amqapi.servercommands.CommandType

@JvmRecord
@CommandType("quiz unpause triggered")
data class QuizResumed(
    val playerName: String,
    val countDownLength: Int,
    val doCountDown: Boolean
)
