package tech.zolhungaj.amqapi.servercommands.gameroom

import com.squareup.moshi.Json
import tech.zolhungaj.amqapi.servercommands.CommandType

@JvmRecord
@CommandType("Game System Chat Message")
data class GameChatSystemMessage(
    @Json(name = "message") val message: String
)
