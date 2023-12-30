package tech.zolhungaj.amqapi.servercommands.globalstate;

import com.squareup.moshi.Json;
import tech.zolhungaj.amqapi.servercommands.CommandType;

import java.util.List;

@CommandType("new quest events")
public record NewQuestEvents(
        @Json(name = "events")
        List<EventsItem> quests
){
    public record EventsItem(
            @Json(name = "questEvent")
            QuestEventState questEventState,
            Data data
    ){}

    public enum QuestEventState{
        @Json(name = "1")
        STATE_UPDATE()
    }
    public record Data(QuestState newState){}

    public record QuestState(
            int questId,
            int ticketReward,
            int noteReward,
            @Json(name = "state") int currentValue,
            @Json(name = "targetState") int targetValue,
            @Json(name = "weekSlot") int dayOfWeek,
            @Json(name = "name") String questName,
            String description
            ) {}
}

