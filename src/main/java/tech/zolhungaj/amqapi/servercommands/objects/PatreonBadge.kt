package tech.zolhungaj.amqapi.servercommands.objects

@JvmRecord
data class PatreonBadge(
    val id: Int,
    val name: String,
    val type: Int,
    val special: Boolean,
    val fileName: String,
    val unlockDescription: String
)
