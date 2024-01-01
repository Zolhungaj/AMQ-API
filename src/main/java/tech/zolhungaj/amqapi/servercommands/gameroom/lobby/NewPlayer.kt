package tech.zolhungaj.amqapi.servercommands.gameroom.lobby

import com.squareup.moshi.Json
import tech.zolhungaj.amqapi.servercommands.CommandType
import tech.zolhungaj.amqapi.servercommands.objects.PlayerAvatar

@JvmRecord
@CommandType("New Player")
data class NewPlayer(
    @Json(name = "level")
    val level: Int,
    @Json(name = "ready")
    val ready: Boolean,
    @Json(name = "name")
    val playerName: String,
    @Json(name = "teamNumber")
    val teamNumber: Int?,
    @Json(name = "gamePlayerId")
    val gamePlayerId: Int,
    @Json(name = "avatar")
    val avatar: PlayerAvatar,
    @Json(name = "inGame")
    val inGame: Boolean
) 