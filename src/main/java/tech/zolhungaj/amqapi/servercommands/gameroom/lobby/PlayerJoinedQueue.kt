package tech.zolhungaj.amqapi.servercommands.gameroom.lobby

import com.squareup.moshi.Json
import tech.zolhungaj.amqapi.servercommands.CommandType

@JvmRecord
@CommandType("new player in game queue")
data class PlayerJoinedQueue(
    @Json(name = "name")
    val playerName: String
)
