package tech.zolhungaj.amqapi.servercommands.social

import com.squareup.moshi.Json
import tech.zolhungaj.amqapi.servercommands.CommandType

@JvmRecord
@CommandType("friend removed")
data class FriendRemoved(
    @Json(name = "name")
    val playerName: String
)