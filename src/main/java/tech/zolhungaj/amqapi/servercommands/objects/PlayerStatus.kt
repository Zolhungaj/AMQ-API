package tech.zolhungaj.amqapi.servercommands.objects

import com.squareup.moshi.Json

enum class PlayerStatus(val text: String) {
    @Json(name = "0")
    OFFLINE("Offline"),

    @Json(name = "1")
    ONLINE("Online"),

    @Json(name = "2")
    DO_NOT_DISTURB("Do Not Disturb"),

    @Json(name = "3")
    AWAY("Away")
}
