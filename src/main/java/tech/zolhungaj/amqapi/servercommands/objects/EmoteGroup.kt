package tech.zolhungaj.amqapi.servercommands.objects

@JvmRecord
data class EmoteGroup(
    val orderNumber: Int,
    val emotes: List<Emote>
)
