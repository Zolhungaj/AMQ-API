package tech.zolhungaj.amqapi.servercommands.gameroom.game

import com.squareup.moshi.Json
import tech.zolhungaj.amqapi.servercommands.CommandType

@JvmRecord
@CommandType("return lobby vote start")
data class ReturnToLobbyVoteStarted(
    @Json(name = "invokingHost") val playerName: String,
    @Json(name = "voteDuration") val voteDurationInSeconds: Int,
)
