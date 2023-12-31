package tech.zolhungaj.amqapi.servercommands.objects.expand

import com.squareup.moshi.Json
import tech.zolhungaj.amqapi.servercommands.objects.SongType

@JvmRecord
data class ExpandLibrarySong(
    @Json(name = "annSongId")
    val songId: Int,
    @Json(name = "type")
    val songType: SongType,
    @Json(name = "number")
    val typeNumber: Int,
    val artist: String,
    @Json(name = "name")
    val songName: String,
    @Json(name = "examples")
    val resolutionUrlMap: Map<String, String>,
    @Json(name = "versions")
    val versionStatus: ExpandLibrarySongVersionEntry
)
