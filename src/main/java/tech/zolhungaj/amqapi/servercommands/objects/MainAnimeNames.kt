package tech.zolhungaj.amqapi.servercommands.objects

import com.squareup.moshi.Json

@JvmRecord
data class MainAnimeNames(
    @Json(name = "romaji") val romaji: String?,
    @Json(name = "english") val english: String?
) 