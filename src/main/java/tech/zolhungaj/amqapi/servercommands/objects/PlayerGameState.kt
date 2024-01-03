package tech.zolhungaj.amqapi.servercommands.objects

import com.squareup.moshi.Json

@JvmRecord
data class PlayerGameState(
    val gameId: Int?,
    val isSpectator: Boolean,
    @Json(name = "private") val isPrivateRoom: Boolean,
    @Json(name = "soloGame") val isSoloGame: Boolean?,
    @Json(name = "isRanked") val isRankedGame: Boolean?,
    val inLobby: Boolean
)
