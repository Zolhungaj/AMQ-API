package tech.zolhungaj.amqapi.servercommands.gameroom.lobby

import tech.zolhungaj.amqapi.servercommands.CommandType

@JvmRecord
@CommandType("Player Ready Change")
data class PlayerReadyChange(
    val ready: Boolean,
    val gamePlayerId: Int
)
