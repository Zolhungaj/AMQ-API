package tech.zolhungaj.amqapi.servercommands.objects

@JvmRecord
data class PlayerEndResult(
    val pose: AvatarPose,
    val endPosition: Int,
    val gamePlayerId: Int
)
