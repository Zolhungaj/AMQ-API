package tech.zolhungaj.amqapi.servercommands.objects

import com.squareup.moshi.Json

@JvmRecord
data class AnimeListSiteShowIds(
    @Json(name = "kitsuId") val kitsuId: Int?,
    @Json(name = "annId") val animeNewsNetworkId: Int?,
    @Json(name = "malId") val myAnimeListId: Int?,
    @Json(name = "aniListId") val aniListId: Int?
) 