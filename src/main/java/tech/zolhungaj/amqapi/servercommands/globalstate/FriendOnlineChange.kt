package tech.zolhungaj.amqapi.servercommands.globalstate

import com.squareup.moshi.Json
import tech.zolhungaj.amqapi.servercommands.CommandType

@JvmRecord
@CommandType("friend state change")
data class FriendOnlineChange(
    @Json(name = "name") val playerName: String,
    val online: Boolean
)
