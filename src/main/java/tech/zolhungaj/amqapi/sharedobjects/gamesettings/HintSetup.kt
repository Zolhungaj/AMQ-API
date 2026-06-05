package tech.zolhungaj.amqapi.sharedobjects.gamesettings

import com.fasterxml.jackson.annotation.JsonProperty
import com.squareup.moshi.Json

data class HintSetup(
    @JsonProperty("audioCost")
    @Json(name = "audioCost")
    val audioCost: Int,
    @JsonProperty("blurVideoCost")
    @Json(name = "blurVideoCost")
    val blurVideoCost: Int,
    @JsonProperty("infoCost")
    @Json(name = "infoCost")
    val infoCost: Int,
    @JsonProperty("multipleChoiceCost")
    @Json(name = "multipleChoiceCost")
    val multipleChoiceCost: Int,
    @JsonProperty("nameCost")
    @Json(name = "nameCost")
    val nameCost: Int,
    @JsonProperty("songPoints")
    @Json(name = "songPoints")
    val songPoints: Int,
    @JsonProperty("tinyVideoCost")
    @Json(name = "tinyVideoCost")
    val tinyVideoCost: Int
){
    companion object {
        @JvmField
        val DEFAULT = HintSetup(
            audioCost = 3,
            blurVideoCost = 3,
            infoCost = 1,
            multipleChoiceCost = 4,
            nameCost = 2,
            songPoints = 5,
            tinyVideoCost = 3)
    }
}
