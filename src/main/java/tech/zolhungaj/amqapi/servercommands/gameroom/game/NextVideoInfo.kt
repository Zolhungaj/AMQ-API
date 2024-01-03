package tech.zolhungaj.amqapi.servercommands.gameroom.game

import com.squareup.moshi.Json
import tech.zolhungaj.amqapi.servercommands.CommandType
import tech.zolhungaj.amqapi.servercommands.objects.VideoInfo

@JvmRecord
@CommandType("quiz next video info")
data class NextVideoInfo(
    @Json(name = "playbackSpeed")
    val playbackSpeed: Double,
    @Json(name = "startPont")//sic
    val startPoint: Double,
    @Json(name = "videoInfo")
    val videoInfo: VideoInfo,
    @Json(name = "playLength")
    val playLength: Int
)
