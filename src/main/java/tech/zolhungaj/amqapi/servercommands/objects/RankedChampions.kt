package tech.zolhungaj.amqapi.servercommands.objects

import com.squareup.moshi.Json

@JvmRecord
data class RankedChampions(
    @Json(name = "1") val central: List<RankedChampion>,
    @Json(name = "2") val western: List<RankedChampion>,
    @Json(name = "3") val eastern: List<RankedChampion>
)
