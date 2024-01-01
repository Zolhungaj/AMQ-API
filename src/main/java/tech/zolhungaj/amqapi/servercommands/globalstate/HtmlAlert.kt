package tech.zolhungaj.amqapi.servercommands.globalstate

import com.squareup.moshi.Json
import tech.zolhungaj.amqapi.servercommands.CommandType

@JvmRecord
@CommandType("html alert")
data class HtmlAlert(
    val title: String,
    val message: String,
    @Json(name = "storeTransactionTrigger") val isFromStoreTransactionTrigger: Boolean
)
