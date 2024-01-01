package tech.zolhungaj.amqapi.servercommands.social

import tech.zolhungaj.amqapi.servercommands.CommandType
import tech.zolhungaj.amqapi.servercommands.objects.messages.DirectMessageEmojis

@JvmRecord
@CommandType("chat message")
data class DirectMessage(
    val emojis: DirectMessageEmojis,
    val sender: String,
    val message: String,
    val modMessage: Boolean
)
