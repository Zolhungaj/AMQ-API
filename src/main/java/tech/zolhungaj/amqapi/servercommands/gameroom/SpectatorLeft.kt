package tech.zolhungaj.amqapi.servercommands.gameroom

import com.squareup.moshi.Json
import tech.zolhungaj.amqapi.servercommands.CommandType

@JvmRecord
@CommandType("Spectator Left")
data class SpectatorLeft(
    val kicked: Boolean,
    val newHost: String?,
    @Json(name = "spectator")
    val playerName: String,
    @Json(name = "readyPingCooldown")
    val readyPingCooldownInSeconds: Int?
)
