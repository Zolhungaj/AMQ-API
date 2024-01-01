package tech.zolhungaj.amqapi.servercommands.objects

import com.squareup.moshi.Json
import tech.zolhungaj.amqapi.constants.RankedSeries
import tech.zolhungaj.amqapi.constants.RankedState

@JvmRecord
data class RankedState(
    val games: ActiveRankedGameModes,
    @Json(name = "serieId") val rankedSeries: RankedSeries?,
    @Json(name = "state") val state: RankedState
)