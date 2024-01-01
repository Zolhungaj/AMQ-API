package tech.zolhungaj.amqapi.servercommands.objects

import com.squareup.moshi.Json

@JvmRecord
data class QuizPlayer(
    @Json(name = "name") val playerName: String,
    @Json(name = "level") val level: Int,
    @Json(name = "gamePlayerId") val gamePlayerId: Int,
    @Json(name = "host") val isHost: Boolean,
    @Json(name = "avatarInfo") val avatarInfo: PlayerAvatar,
    @Json(name = "inGame") val inGame: Boolean,
    @Json(name = "teamPlayer") val teamPlayer: Boolean?,
    @Json(name = "teamNumber") val teamNumber: Int?,
    @Json(name = "pose") val pose: AvatarPose,
    @Json(name = "score") val score: Int,
    @Json(name = "teamCaptain") val teamCaptain: Boolean?,
    @Json(name = "multiChoiceActive") val hasMultiChoiceActive: Boolean,
    @Json(name = "position") val position: Int,
    @Json(name = "positionSlot") val positionSlot: Int?
) 