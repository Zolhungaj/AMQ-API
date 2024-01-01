package tech.zolhungaj.amqapi.servercommands.globalstate

import com.squareup.moshi.Json
import tech.zolhungaj.amqapi.servercommands.CommandType

@JvmRecord
@CommandType("server restart")
data class ServerRestartWarning(
    @Json(name = "msg") val message: String,
    @Json(name = "time") val timeInMinutes: Int
)
