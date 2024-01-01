package tech.zolhungaj.amqapi.servercommands.gameroom

import tech.zolhungaj.amqapi.servercommands.CommandType
import tech.zolhungaj.amqapi.servercommands.objects.messages.Buble

@JvmRecord
@CommandType("game chat update")
data class GameChatUpdate(
    val messages: List<GameChatMessage>,
    val bubles: List<Buble>
)
