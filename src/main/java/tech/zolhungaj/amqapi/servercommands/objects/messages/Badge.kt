package tech.zolhungaj.amqapi.servercommands.objects.messages

import com.squareup.moshi.Json
import tech.zolhungaj.amqapi.servercommands.objects.Localisation

@JvmRecord
data class Badge(
    @Json(name = "fileName") val filename: String,
    @Json(name = "name") val badgeName: Localisation
)
