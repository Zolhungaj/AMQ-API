package tech.zolhungaj.amqapi.servercommands.objects

import com.squareup.moshi.Json

@JvmRecord
data class ProgressBarState(
    @field:Json(name = "length") @param:Json(name = "length") val totalLength: Double,
    @field:Json(name = "played") @param:Json(name = "played") val currentPosition: Double
) 