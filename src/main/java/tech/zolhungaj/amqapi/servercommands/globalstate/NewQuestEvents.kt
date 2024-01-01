package tech.zolhungaj.amqapi.servercommands.globalstate

import com.squareup.moshi.Json
import tech.zolhungaj.amqapi.servercommands.CommandType
import tech.zolhungaj.amqapi.servercommands.objects.QuestEventItem

@JvmRecord
@CommandType("new quest events")
data class NewQuestEvents(
    @Json(name = "events") val quests: List<QuestEventItem>
)

