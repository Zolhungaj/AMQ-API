package tech.zolhungaj.amqapi.servercommands.objects

@JvmRecord
data class QuestDescription(
    val ticketReward: Int,
    val questId: Int,
    val targetState: Int,
    val weekSlot: QuestStateWeekSlot,
    val name: String,
    val description: String,
    val state: Int,
    val noteReward: Int
)
