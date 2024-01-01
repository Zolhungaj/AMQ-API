package tech.zolhungaj.amqapi.servercommands.gameroom.lobby

import com.squareup.moshi.Json
import tech.zolhungaj.amqapi.servercommands.CommandType
import tech.zolhungaj.amqapi.servercommands.objects.PlayerAvatar

@JvmRecord
@CommandType("Spectator Change To Player")
data class SpectatorChangedToPlayer(
    @Json(name = "name")
    val playerName: String,
    val gamePlayerId: Int,
    val level: Int,
    val avatar: PlayerAvatar,
    val ready: Boolean,
    val inGame: Boolean,
    val teamNumber: Int?
)
