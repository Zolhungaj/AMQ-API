package tech.zolhungaj.amqapi.servercommands.gameroom.game

import com.squareup.moshi.Json
import tech.zolhungaj.amqapi.servercommands.CommandType

@JvmRecord
@CommandType("quiz pause triggered")
data class QuizPaused(
    val playerName: String?,
    @Json(name = "noPlayerPause") val allPlayersDisconnected: Boolean?
)
