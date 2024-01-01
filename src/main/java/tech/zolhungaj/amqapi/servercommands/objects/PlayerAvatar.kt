package tech.zolhungaj.amqapi.servercommands.objects

@JvmRecord
data class PlayerAvatar(
    val avatar: PlayerAvatarDescription,
    val background: PlayerAvatarBackgroundDescription
)
