package tech.zolhungaj.amqapi.servercommands.gameroom.game

import tech.zolhungaj.amqapi.servercommands.CommandType
import tech.zolhungaj.amqapi.servercommands.objects.VideoInfo

@JvmRecord
@CommandType("quiz next video info")
data class NextVideoInfo(
    val playbackSpeed: Double,
    val startPoint: Double,
    val videoInfo: VideoInfo,
    val playLength: Int
)
