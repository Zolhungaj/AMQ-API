package tech.zolhungaj.amqapi.servercommands.objects

import com.squareup.moshi.Json

@JvmRecord
data class QuestEventState(
    val questId: Int,
    val ticketReward: Int,
    val noteReward: Int,
    @Json(name = "state") val currentValue: Int,
    @Json(name = "targetState") val targetValue: Int,
    @Json(name = "weekSlot") val dayOfWeek: Int,//TODO fix to handle "event"
    @Json(name = "name") val questName: String,
    val description: String
)
