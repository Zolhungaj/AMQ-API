package tech.zolhungaj.amqapi.servercommands.objects

import com.squareup.moshi.Json

@JvmRecord
data class SongInfo(
    @Json(name = "songName") val songName: String,
    @Json(name = "animeGenre") val animeGenre: List<String>,

    @Json(name = "animeNames") val mainAnimeNames: MainAnimeNames,
    @Json(name = "artist") val artist: String,

    @Json(name = "animeDifficulty") val animeDifficulty: Double?,

    @Json(name = "altAnimeNames") val alternativeAnimeNames: List<String>,

    @Json(name = "animeScore") val animeScore: Double?,
    @Json(name = "type") val type: SongType,

    @Json(name = "videoTargetMap") val videoTargetMap: Map<String, Map<String, String>>,

    @Json(name = "typeNumber") val typeNumber: Int,
    @Json(name = "annId") val annId: Int,

    @Json(name = "vintage") val vintage: String,
    @Json(name = "animeTags") val animeTags: List<String>,

    @Json(name = "siteIds") val siteIds: AnimeListSiteShowIds,
    @Json(name = "animeType") val animeType: String,
    @Json(name = "highRisk") val highRisk: Boolean,

    @Json(name = "altAnimeNamesAnswers") val alternativeAnimeNamesAnswers: List<String>
) 