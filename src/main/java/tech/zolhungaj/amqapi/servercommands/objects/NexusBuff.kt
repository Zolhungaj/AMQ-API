package tech.zolhungaj.amqapi.servercommands.objects

import com.squareup.moshi.Json

@JvmRecord
data class NexusBuff(
    @Json(name = "localizationData") val localisationParameters : Map<String, String>?,
    @Json(name = "nameKey") val localisationNameKey: String,
    @Json(name = "descriptionKey") val localisationDescriptionKey: String,
    @Json(name = "fileName") val filename: String,
    @Json(name = "debuff") val isDebuff: Boolean,
    @Json(name = "name") val buffInternalName: String
)