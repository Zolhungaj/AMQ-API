package tech.zolhungaj.amqapi.servercommands.objects

@JvmRecord
data class RecentTicketReward(
    val tier: Int,
    val description: RecentTicketRewardDescription,
    val type: String,
    val rhythm: Int
)
