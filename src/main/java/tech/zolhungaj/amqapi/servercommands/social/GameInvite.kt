package tech.zolhungaj.amqapi.servercommands.social

import tech.zolhungaj.amqapi.servercommands.CommandType

@JvmRecord
@CommandType("game invite")
data class GameInvite(
    val gameId: Int?,
    val sender: String
)
