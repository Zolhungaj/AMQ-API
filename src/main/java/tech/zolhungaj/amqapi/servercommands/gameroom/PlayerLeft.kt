package tech.zolhungaj.amqapi.servercommands.gameroom

import tech.zolhungaj.amqapi.servercommands.CommandType
import tech.zolhungaj.amqapi.servercommands.objects.PlayerIdentifier

@JvmRecord
@CommandType("Player Left")
data class PlayerLeft(
    val kicked: Boolean,
    val disconnect: Boolean,
    val newHost: String?,
    val player: PlayerIdentifier
)
