package tech.zolhungaj.amqapi.constants

import com.squareup.moshi.Json

enum class RankedState(val message: String) {
    @Json(name = "0")
    OFFLINE("Offline"),
    @Json(name = "1")
    LOBBY("Lobby Up"),
    @Json(name = "2")
    RUNNING("Playing"),
    @Json(name = "3")
    FINISHED("Completed"),
    @Json(name = "4")
    CHAMP_OFFLINE("Championship - Offline"),
    @Json(name = "5")
    CHAMP_LOBBY("Championship - Lobby"),
    @Json(name = "6")
    CHAMP_RUNNING("Championship - Playing"),
    @Json(name = "7")
    CHAMP_FINISHED("Completed"),
    @Json(name = "8")
    BREAK_DAY("Ranked Rest Day"),
    @Json(name = "9")
    THEMED_OFFLINE("Themed - Offline"),
    @Json(name = "10")
    THEMED_LOBBY("Themed - Lobby"),
    @Json(name = "11")
    THEMED_RUNNING("Themed - Playing"),
    @Json(name = "12")
    THEMED_FINISHED("Themed - Completed"),
}
