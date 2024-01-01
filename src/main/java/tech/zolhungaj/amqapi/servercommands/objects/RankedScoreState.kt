package tech.zolhungaj.amqapi.servercommands.objects

@JvmRecord
data class RankedScoreState(
    val rank: Int,
    val score: Int
)
