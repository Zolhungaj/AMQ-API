package tech.zolhungaj.amqapi.servercommands.objects

@JvmRecord
data class VideoWithVolumeAndResolution(
    val video: String,
    val volume: Int?,
    val resolution: Int
)
