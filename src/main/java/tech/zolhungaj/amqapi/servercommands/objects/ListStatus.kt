package tech.zolhungaj.amqapi.servercommands.objects;

import com.squareup.moshi.Json;

public enum ListStatus {
    @Json(name = "1")
    WATCHING,
    @Json(name = "2")
    COMPLETED,
    @Json(name = "3")
    ON_HOLD,
    @Json(name = "4")
    DROPPED,
    @Json(name = "5")
    PLAN_TO_WATCH,
    @Json(name = "6")
    LOOTED
}
