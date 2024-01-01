package tech.zolhungaj.amqapi.servercommands.gameroom.lobby

import tech.zolhungaj.amqapi.servercommands.CommandType
import tech.zolhungaj.amqapi.servercommands.objects.PlayerAvatar

@JvmRecord
@CommandType("avatar change")
data class PlayerChangedAvatar(
    val gamePlayerId: Int,
    val avatar: PlayerAvatar
)
