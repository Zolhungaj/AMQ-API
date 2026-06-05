package tech.zolhungaj.amqapi.sharedobjects.gamesettings

import com.fasterxml.jackson.annotation.JsonProperty
import com.squareup.moshi.Json

data class GuessMode(
    @JsonProperty("song")
    @Json(name = "song")
    val song: Boolean,
    @JsonProperty("tinyVideo")
    @Json(name = "tinyVideo")
    val tinyVideo: Boolean,
    @JsonProperty("blurVideo")
    @Json(name = "blurVideo")
    val blurVideo: Boolean
){
    companion object {
        @JvmField
        val DEFAULT = GuessMode(song = true, tinyVideo = false, blurVideo = false)
    }
}
