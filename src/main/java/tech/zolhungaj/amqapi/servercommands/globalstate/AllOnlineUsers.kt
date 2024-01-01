package tech.zolhungaj.amqapi.servercommands.globalstate

import com.squareup.moshi.Json
import tech.zolhungaj.amqapi.servercommands.CommandType

@JvmRecord
@CommandType("all online users")
data class AllOnlineUsers(
    @Json(name = "array") val list: List<String>
)
