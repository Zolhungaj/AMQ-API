package tech.zolhungaj.amqapi.servercommands.objects

@JvmRecord
data class RewardAlert(
    val fileName: String,
    val name: String,
    val userRewardAlertId: Int
)
