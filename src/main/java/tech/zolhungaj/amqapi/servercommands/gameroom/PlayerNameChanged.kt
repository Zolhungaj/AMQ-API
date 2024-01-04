package tech.zolhungaj.amqapi.servercommands.gameroom

import tech.zolhungaj.amqapi.servercommands.CommandType

@JvmRecord
@CommandType("player name change")
data class PlayerNameChanged(
    val gamePlayerId: Int,
    val oldName: String,
    val newName: String,
    val isHost: Boolean
)