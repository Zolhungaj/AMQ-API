package tech.zolhungaj.amqapi.servercommands.gameroom.game

import com.squareup.moshi.Json
import tech.zolhungaj.amqapi.servercommands.CommandType

@JvmRecord
@CommandType("quiz waiting buffering")
data class WaitingForBuffering(
    @Json(name = "firstSong")
    val isFirstSong: Boolean
)
