package tech.zolhungaj.amqapi.servercommands.objects

@JvmRecord
data class SaleInfo(
    val endDate: String?,
    val unlocksLeft: Int
)
