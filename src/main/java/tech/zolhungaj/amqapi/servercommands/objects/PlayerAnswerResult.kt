package tech.zolhungaj.amqapi.servercommands.objects

import com.squareup.moshi.Json

@JvmRecord
data class PlayerAnswerResult(
    @Json(name = "positionSlot") val positionSlot: Int,
    @Json(name = "score") val score: Int,
    @Json(name = "correct") val correct: Boolean,
    @Json(name = "pose") val pose: AvatarPose,
    @Json(name = "level") val level: Int,
    @Json(name = "listStatus") val listStatus: ListStatus?,
    @Json(name = "showScore") private val showScoreJson: Int?,
    @Json(name = "gamePlayerId") val gamePlayerId: Int,
    @Json(name = "position") val position: Int
) {
    val showScore: Int?
        get() = if (showScoreJson == 0) null else showScoreJson
}