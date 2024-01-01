package tech.zolhungaj.amqapi.servercommands.expandlibrary

import com.squareup.moshi.Json
import tech.zolhungaj.amqapi.servercommands.CommandType

@JvmRecord
@CommandType("expandLibrary song answered")
data class ExpandLibraryEntryUpdated(
    @Json(name = "annId")
    val animeNewsNetworkId: Int,
    @Json(name = "annSongId")
    val songId : Int,
    val host: String,
    val resolution: Int,
    @Json(name = "animeStillAvaliable")
    val animeStillAvailable : Boolean,
    @Json(name = "songStillAvaliable")
    val songStillAvailable : Boolean?
)
