package tech.zolhungaj.amqapi.servercommands.objects

import com.squareup.moshi.Json

enum class QuestEventStateType {
    @Json(name = "1")
    STATE_UPDATE
}
