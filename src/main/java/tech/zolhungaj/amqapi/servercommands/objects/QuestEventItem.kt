package tech.zolhungaj.amqapi.servercommands.objects

import com.squareup.moshi.Json

@JvmRecord
data class QuestEventItem(
    @Json(name = "questEvent") val questEventStateType: QuestEventStateType,
    val data: Data
) {
    @JvmRecord
    data class Data(val newState: QuestEventState)
}
