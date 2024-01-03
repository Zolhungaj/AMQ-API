package tech.zolhungaj.amqapi.servercommands.gameroom.game

import com.squareup.moshi.Json
import tech.zolhungaj.amqapi.servercommands.CommandType

@JvmRecord
@CommandType("return lobby vote result")
data class ReturnToLobbyVoteResult(
    @Json(name = "reason") val reason: String?,
    @Json(name = "passed") val passed: Boolean,
    @Json(name = "timeout") val timeout: Int, // unused?
)
