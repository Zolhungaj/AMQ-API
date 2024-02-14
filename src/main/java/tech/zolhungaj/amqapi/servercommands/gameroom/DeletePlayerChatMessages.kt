package tech.zolhungaj.amqapi.servercommands.gameroom

import tech.zolhungaj.amqapi.servercommands.CommandType

@JvmRecord
@CommandType("delete player messages")
data class DeletePlayerChatMessages(
    val playerName: String
)
