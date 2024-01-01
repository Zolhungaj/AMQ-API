package tech.zolhungaj.amqapi.servercommands.gameroom.game

import tech.zolhungaj.amqapi.servercommands.CommandType

@JvmRecord
@CommandType("quiz ready")
data class QuizReady(
    val numberOfSongs: Int
)
