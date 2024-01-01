package tech.zolhungaj.amqapi.servercommands.objects

@JvmRecord
data class VideoMapWithVolume(
    val video1080p: VideoWithVolumeAndResolution?,
    val video720p: VideoWithVolumeAndResolution?,
    val video480p: VideoWithVolumeAndResolution?,
    val audio: VideoWithVolumeAndResolution?
) {
    constructor(map: Map<Int, VideoWithVolumeAndResolution>) : this(
        map[1080],
        map[720],
        map[480],
        map[0]
    )

    companion object {
        @JvmField
        val VALID_RESOLUTIONS: List<Int> = listOf(1080, 720, 480, 0)
    }
}
