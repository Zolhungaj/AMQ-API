package tech.zolhungaj.amqapi.servercommands.gameroom.lobby

import com.squareup.moshi.Json
import tech.zolhungaj.amqapi.servercommands.CommandType

@JvmRecord
@CommandType("player left queue")
data class PlayerLeftQueue(
    @Json(name = "name")
    val playerName: String
)
