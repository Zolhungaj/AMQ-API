package tech.zolhungaj.amqapi.servercommands.objects

import com.squareup.moshi.Json
import java.util.*

@JvmRecord
data class PlayerGameState(
    val gameId: Optional<Int>,
    val isSpectator: Boolean,
    @Json(name = "private") val isPrivateRoom: Boolean,
    @Json(name = "soloGame") val isSoloGame: Boolean?,
    @Json(name = "isRanked") val isRankedGame: Boolean?,
    val inLobby: Boolean
)
