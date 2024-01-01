package tech.zolhungaj.amqapi.servercommands.globalstate

import com.squareup.moshi.Json
import tech.zolhungaj.amqapi.servercommands.CommandType
import tech.zolhungaj.amqapi.servercommands.objects.RankedChampion

@JvmRecord
@CommandType("ranked champions updated")
data class RankedChampionsUpdate(
    val champions: List<RankedChampion>,
    @Json(name = "serieId") val seriesId: Int
)