package tech.zolhungaj.amqapi.servercommands.objects.messages

@JvmRecord
data class Buble(
    val emoteIds: List<String>,
    val shortCodes: List<String>
)
