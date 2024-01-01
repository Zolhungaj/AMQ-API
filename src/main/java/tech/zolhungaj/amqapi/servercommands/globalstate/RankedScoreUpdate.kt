package tech.zolhungaj.amqapi.servercommands.globalstate

import com.squareup.moshi.Json
import tech.zolhungaj.amqapi.servercommands.CommandType
import tech.zolhungaj.amqapi.servercommands.objects.RankedScoreState
import java.util.*

@JvmRecord
@CommandType("ranked score update")
data class RankedScoreUpdate(
    val position: Int,
    val oldState: RankedScoreState?,
    val newState: RankedScoreState,
    @Json(name = "oldBadge") val oldBadgeFilename: String,
    @Json(name = "newBadge") val newBadgeFilename: String
)
