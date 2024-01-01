package tech.zolhungaj.amqapi.servercommands.globalstate

import com.squareup.moshi.Json
import tech.zolhungaj.amqapi.servercommands.CommandType

@JvmRecord
@CommandType("server state change")
data class FileServerStatus(
    @Json(name = "name") val serverName: String,
    val online: Boolean
)
