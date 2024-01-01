package tech.zolhungaj.amqapi.servercommands.gameroom.lobby

import tech.zolhungaj.amqapi.servercommands.CommandType
import tech.zolhungaj.amqapi.servercommands.objects.PlayerIdentifier

@JvmRecord
@CommandType("Player Changed To Spectator")
data class PlayerChangedToSpectator(
    val spectatorDescription: PlayerIdentifier,
    val playerDescription: PlayerIdentifier,
    val isHost: Boolean
)
