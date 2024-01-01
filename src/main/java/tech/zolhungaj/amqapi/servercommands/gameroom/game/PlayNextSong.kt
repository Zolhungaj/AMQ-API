package tech.zolhungaj.amqapi.servercommands.gameroom.game

import com.squareup.moshi.Json
import tech.zolhungaj.amqapi.servercommands.CommandType
import tech.zolhungaj.amqapi.servercommands.objects.ProgressBarState

@JvmRecord
@CommandType("play next song")
data class PlayNextSong(
    @Json(name = "songNumber")
    val songNumber: Int,
    @Json(name = "onLastSong")
    val onLastSong: Boolean,
    @Json(name = "multipleChoiceNames")
    val multipleChoiceNames: List<String>?,
    @Json(name = "time")
    val time: Double,
    @Json(name = "extraGuessTime")
    val extraGuessTime: Int,
    @Json(name = "progressBarState")
    val progressBarState: ProgressBarState
)
