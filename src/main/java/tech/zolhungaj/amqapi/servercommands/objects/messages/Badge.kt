package tech.zolhungaj.amqapi.servercommands.objects.messages

import com.squareup.moshi.Json

@JvmRecord
data class Badge(
    @Json(name = "fileName") val filename: String,
    @Json(name = "name") val badgeName: String
)
