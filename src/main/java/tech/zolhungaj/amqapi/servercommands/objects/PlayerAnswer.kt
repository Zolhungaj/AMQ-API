package tech.zolhungaj.amqapi.servercommands.objects

@JvmRecord
data class PlayerAnswer(
    val answer: String,
    val pose: AvatarPose,
    val gamePlayerId: Int
)
