package tech.zolhungaj.amqapi.servercommands.globalstate

import com.squareup.moshi.Json
import tech.zolhungaj.amqapi.constants.RankedSeries
import tech.zolhungaj.amqapi.constants.RankedState
import tech.zolhungaj.amqapi.servercommands.CommandType

@JvmRecord
@CommandType("ranked game state change")
data class RankedGameStateChanged(
    @Json(name = "serieId") val rankedSeries: RankedSeries,
    @Json(name = "state") val state: RankedState
)
