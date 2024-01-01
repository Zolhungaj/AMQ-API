package tech.zolhungaj.amqapi.servercommands.objects

import com.squareup.moshi.Json

@JvmRecord
data class SalesTax(
    @field:Json(name = "old") @param:Json(name = "old") val oldValue: Double,
    @field:Json(name = "new") @param:Json(name = "new") val newValue: Double
)
