package tech.zolhungaj.amqapi.servercommands.objects

import com.squareup.moshi.Json

@JvmRecord
data class RankedLeaderboard(
    @Json(name = "1") val central: List<RankedLeaderboardEntry>,
    @Json(name = "2") val western: List<RankedLeaderboardEntry>,
    @Json(name = "3") val eastern: List<RankedLeaderboardEntry>
)
