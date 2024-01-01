package tech.zolhungaj.amqapi.servercommands.objects

@JvmRecord
data class RollTarget(
    val fileName: String,
    val name: String,
    val id: Int
)
