package tech.zolhungaj.amqapi.servercommands.objects;

import com.squareup.moshi.Json;

public enum SongType {
    @Json(name = "1")
    OPENING,
    @Json(name = "2")
    ENDING,
    @Json(name = "3")
    INSERT,
    @Json(name = "0")
    UNKNOWN
}
