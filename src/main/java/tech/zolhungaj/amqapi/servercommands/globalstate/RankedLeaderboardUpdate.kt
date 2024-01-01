package tech.zolhungaj.amqapi.servercommands.globalstate

import com.squareup.moshi.Json
import tech.zolhungaj.amqapi.constants.RankedSeries
import tech.zolhungaj.amqapi.servercommands.CommandType
import tech.zolhungaj.amqapi.servercommands.objects.RankedLeaderboardEntry

@JvmRecord
@CommandType("ranked standing updated")
data class RankedLeaderboardUpdate(
    @Json(name = "serieId") val rankedSeries: RankedSeries,
    @Json(name = "standings") val leaderboard: List<RankedLeaderboardEntry>
)
