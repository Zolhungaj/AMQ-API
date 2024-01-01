package tech.zolhungaj.amqapi.servercommands.globalstate

import com.squareup.moshi.Json
import tech.zolhungaj.amqapi.servercommands.CommandType

@JvmRecord
@CommandType("online user change")
data class OnlineUserChange(
    @Json(name = "name") val username: String,
    val online: Boolean
) 