package tech.zolhungaj.amqapi.servercommands.objects

@JvmRecord
data class ActiveRankedGameModes(
    val expert: Boolean,
    val novice: Boolean
)
