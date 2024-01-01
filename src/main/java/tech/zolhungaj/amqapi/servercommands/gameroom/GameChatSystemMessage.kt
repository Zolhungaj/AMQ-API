package tech.zolhungaj.amqapi.servercommands.gameroom

import com.squareup.moshi.Json
import tech.zolhungaj.amqapi.servercommands.CommandType

@JvmRecord
@CommandType("Game System Chat Message")
data class GameChatSystemMessage(
    val title: String,
    @Json(name = "msg")
    val message: String?,
    @Json(name = "teamMessage")
    val isTeamMessage: Boolean
)
