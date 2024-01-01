package tech.zolhungaj.amqapi.constants

import com.squareup.moshi.Json
import java.util.*

enum class RankedSeries(val seriesName: String, val symbol: Char, val timeZone: TimeZone) {
    @Json(name = "1")
    CENTRAL("Central", 'C', TimeZone.getTimeZone("Europe/Copenhagen")),
    @Json(name = "2")
    WEST("West", 'W', TimeZone.getTimeZone("America/Chicago")),
    @Json(name = "3")
    EAST("East", 'E', TimeZone.getTimeZone("Asia/Tokyo")),
}
