package tech.zolhungaj.amqapi.servercommands.gameroom

import com.squareup.moshi.Json
import tech.zolhungaj.amqapi.servercommands.CommandType

@JvmRecord
@CommandType("New Spectator")
data class SpectatorJoined(
    @Json(name = "name")
    val playerName: String,
    val gamePlayerId: Int? //Usually null, might be set in ranked??
)
