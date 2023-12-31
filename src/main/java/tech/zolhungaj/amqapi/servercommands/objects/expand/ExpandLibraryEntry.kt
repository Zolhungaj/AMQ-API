package tech.zolhungaj.amqapi.servercommands.objects.expand

import com.squareup.moshi.Json

data class ExpandLibraryEntry(
    @Json(name = "name") val animeName: String,
    @Json(name = "annId") val animeNewsNetworkId: Int,
    val songs: List<ExpandLibrarySong>
)
