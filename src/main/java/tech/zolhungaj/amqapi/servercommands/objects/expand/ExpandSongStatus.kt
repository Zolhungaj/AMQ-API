package tech.zolhungaj.amqapi.servercommands.objects.expand

import com.squareup.moshi.Json

enum class ExpandSongStatus {
    @Json(name = "1")
    APPROVED,

    @Json(name = "2")
    PENDING,

    @Json(name = "3")
    MISSING,

    @Json(name = "0")
    UNKNOWN
}