package tech.zolhungaj.amqapi.servercommands.objects

import com.squareup.moshi.Json

@JvmRecord
data class NameDecorationOption(
    val id: Int,
    @Json(name = "name") val decorationName: String,
    @Json(name = "className") val cssClassName: String,
    val unlockDescription: String,
)
