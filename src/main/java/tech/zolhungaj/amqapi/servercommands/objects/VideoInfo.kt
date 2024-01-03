package tech.zolhungaj.amqapi.servercommands.objects

import com.squareup.moshi.Json

@JvmRecord
data class VideoInfo(
    @Json(name = "videoMap") val videoMap: Map<String, Map<String, String>>,
    @Json(name = "videoVolumeMap") val videoVolumeMap: Map<String, Map<String, Int>>,
    @Json(name = "id") val id: Int,
    @Json(name = "encrypted") val isEncrypted: Boolean
) {
    fun catbox(): VideoMapWithVolume? {
        val videos = videoMap[CATBOX] ?: return null
        val volumes = videoVolumeMap[CATBOX]
        val resolutionMap: MutableMap<Int, VideoWithVolumeAndResolution> = HashMap()
        for ((key, video) in videos) {
            val resolution = key.toInt()
            val volume = volumes?.get(key)
            val newEntry = VideoWithVolumeAndResolution(video, volume, resolution)
            if (VideoMapWithVolume.VALID_RESOLUTIONS.contains(resolution)) {
                resolutionMap[resolution] = newEntry
            } else {
                error("Unknown resolution $resolution for catbox video $video")
            }
        }
        return VideoMapWithVolume(resolutionMap)
    }

    fun openingsmoe(): VideoWithVolumeAndResolution? {
        val videos = videoMap[OPENINGS_MOE] ?: return null
        val resolution = videos.keys.firstOrNull() ?: return null
        val video = videos[resolution] ?: return null
        val volume = videoVolumeMap[OPENINGS_MOE]?.get(resolution)
        return VideoWithVolumeAndResolution(video, volume, resolution.toInt())
    }

    companion object {
        private const val CATBOX = "catbox"
        private const val OPENINGS_MOE = "openingsmoe"
    }
}