package tech.zolhungaj.amqapi.servercommands.objects

import com.squareup.moshi.Json

@JvmRecord
data class LatestQuizSettings(
    val solo: String?,
    @Json(name = "multi") val multiplayer: String?,
)
