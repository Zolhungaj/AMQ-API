package tech.zolhungaj.amqapi.servercommands.objects

@JvmRecord
data class UniqueBackground(
    val fileName: String,
    val uniqueBackgroundId: Int,
    val name: String,
    val artistName: String,
    val tierId: Int,
)