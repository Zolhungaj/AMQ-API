package tech.zolhungaj.amqapi.servercommands.objects

import com.squareup.moshi.Json

@JvmRecord
data class RankedLeaderboardEntry(
    val score: Int,
    @Json(name = "name") val playerName: String?,
    val position: Int
) : Comparable<RankedLeaderboardEntry> {
    override fun compareTo(other: RankedLeaderboardEntry): Int {
        return this.position - other.position
    }
}
