package tech.zolhungaj.amqapi.servercommands.gameroom.game

import com.squareup.moshi.Json
import tech.zolhungaj.amqapi.servercommands.CommandType

@JvmRecord
@CommandType("quiz overlay message")
data class SkipVotePassed(
    @Json(name = "string")
    val message: String
)