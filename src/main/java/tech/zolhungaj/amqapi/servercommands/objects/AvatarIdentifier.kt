package tech.zolhungaj.amqapi.servercommands.objects

@JvmRecord
data class AvatarIdentifier(
    val avatarId: Int,
    val colorId: Int,
    val optionActive: Boolean
)
