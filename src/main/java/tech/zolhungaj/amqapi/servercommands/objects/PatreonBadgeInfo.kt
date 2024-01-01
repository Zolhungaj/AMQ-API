package tech.zolhungaj.amqapi.servercommands.objects

@JvmRecord
data class PatreonBadgeInfo(
    val next: PatreonBadge,
    val current: PatreonBadge
)
