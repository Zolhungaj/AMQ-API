package tech.zolhungaj.amqapi.servercommands.gameroom.game

import com.squareup.moshi.Json
import tech.zolhungaj.amqapi.servercommands.CommandType
import tech.zolhungaj.amqapi.servercommands.objects.PlayerAnswerResult
import tech.zolhungaj.amqapi.servercommands.objects.ProgressBarState
import tech.zolhungaj.amqapi.servercommands.objects.SongInfo

@JvmRecord
@CommandType("answer results")
data class AnswerResults(
    @Json(name = "watched")
    val isWatched: Boolean,
    @Json(name = "groupMap")
    val groupMap: Map<String, List<Int>>,
    @Json(name = "players")
    val players: List<PlayerAnswerResult>,
    @Json(name = "songInfo")
    val songInfo: SongInfo,
    @Json(name = "progressBarState")
    val progressBarState: ProgressBarState
)
