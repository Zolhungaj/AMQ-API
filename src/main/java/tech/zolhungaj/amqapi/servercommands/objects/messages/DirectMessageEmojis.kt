package tech.zolhungaj.amqapi.servercommands.objects.messages

@JvmRecord
data class DirectMessageEmojis(
    val shortCodes: List<String>,
    val emotes: List<Int>,
    val customEmojis: List<CustomEmoji>
)
