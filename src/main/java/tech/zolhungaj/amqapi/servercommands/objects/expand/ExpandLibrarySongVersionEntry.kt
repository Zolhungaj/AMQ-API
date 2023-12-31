package tech.zolhungaj.amqapi.servercommands.objects.expand

import com.squareup.moshi.Json

@JvmRecord
data class ExpandLibrarySongVersionEntry(
    @Json(name = "closed")
    val closedMap: Map<String, ExpandLibrarySongVersionEntryClosed>,
    @Json(name = "open")
    val openStatusMap: Map<String, Map<String, ExpandSongStatus>>
)
