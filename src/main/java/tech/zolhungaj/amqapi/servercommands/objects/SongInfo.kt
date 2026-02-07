package tech.zolhungaj.amqapi.servercommands.objects

import com.squareup.moshi.Json

@JvmRecord
data class SongInfo(
    @Json(name = "songName") val songName: String,
    @Json(name = "animeGenre") val animeGenre: List<String>,

    @Json(name = "animeNames") val mainAnimeNames: MainAnimeNames,
    @Json(name = "artist") val artist: String,

    @Json(name = "animeDifficulty") val animeDifficulty: AnimeDifficulty,
    @Json(name = "seasonInfo") val season: SeasonInfo?,

    @Json(name = "altAnimeNames") val alternativeAnimeNames: List<String>,

    @Json(name = "animeScore") val animeScore: Double?,
    @Json(name = "composerInfo") val composerInfo: ArtistInfo?,

    @Json(name = "type") val type: SongType,
    @Json(name = "dub") val isDub: Boolean,
    @Json(name = "artistInfo") val artistInfo: ArtistInfo?,


    @Json(name = "videoTargetMap") val videoTargetMap: Map<String, Map<String, String>>,

    @Json(name = "typeNumber") val typeNumber: Int,
    @Json(name = "annId") val annId: Int,

    @Json(name = "vintage") val vintage: Vintage,
    @Json(name = "animeTags") val animeTags: List<String>,

    @Json(name = "siteIds") val siteIds: AnimeListSiteShowIds,
    @Json(name = "arrangerInfo") val arrangerInfo: ArtistInfo?,

    @Json(name = "animeType") val animeType: String,
    @Json(name = "highRisk") val isHighRisk: Boolean,
    @Json(name = "rebroadcast") val isRebroadcast: Boolean,

    @Json(name = "altAnimeNamesAnswers") val alternativeAnimeNamesAnswers: List<String>
){
    data class SeasonInfo(val number: String?, val name: String)
    data class ArtistInfo(val name: String, val artistId: Int?, val groupId: Int?)
    @JvmRecord
    data class Vintage(val key: String, val data: VintageData){
        data class VintageData(val year: Int)
    }
}