package tech.zolhungaj.amqapi.servercommands.objects

import com.squareup.moshi.Json

@JvmRecord
data class RankedChampion(
    @Json(name = "name") val playerName: String,
    val position: Int
) : Comparable<RankedChampion> {
    override fun compareTo(other: RankedChampion): Int {
        return this.position - other.position
    }
}