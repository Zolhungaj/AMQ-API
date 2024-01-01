package tech.zolhungaj.amqapi.servercommands.objects

@JvmRecord
data class PersonalCustomEmoji(
    val validated: Boolean,
    val name: String,
    val active: Boolean,
    val id: Int,
    val type: Int
)
