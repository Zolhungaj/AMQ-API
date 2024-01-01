package tech.zolhungaj.amqapi.servercommands.objects.messages

@JvmRecord
data class MessageEmoji(
    val emotes: List<Int>,
    val customEmojis: List<CustomEmoji>
)
