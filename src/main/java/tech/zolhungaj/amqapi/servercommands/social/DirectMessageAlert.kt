package tech.zolhungaj.amqapi.servercommands.social

import com.squareup.moshi.Json
import tech.zolhungaj.amqapi.servercommands.CommandType

@JvmRecord
@CommandType("new chat alert")
data class DirectMessageAlert(
    @Json(name = "name") val playerName: String,
    @Json(name = "alert") val alert: String
)