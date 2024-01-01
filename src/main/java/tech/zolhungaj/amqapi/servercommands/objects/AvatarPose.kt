package tech.zolhungaj.amqapi.servercommands.objects

import com.squareup.moshi.Json

enum class AvatarPose {
    @Json(name = "1")
    BASIC,

    @Json(name = "2")
    THINKING,

    @Json(name = "3")
    WAITING,

    @Json(name = "4")
    WRONG,

    @Json(name = "5")
    RIGHT,

    @Json(name = "6")
    CONFUSED
}
