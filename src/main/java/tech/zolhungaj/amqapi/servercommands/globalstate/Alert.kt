package tech.zolhungaj.amqapi.servercommands.globalstate

import com.squareup.moshi.Json
import tech.zolhungaj.amqapi.servercommands.CommandType

@JvmRecord
@CommandType("alert")
data class Alert(
    val title: String,
    val message: String,
    @Json(name = "easyClose") val allowOutsideClickToClose: Boolean?
)
