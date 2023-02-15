package tech.zolhungaj.amqapi.servercommands.globalstate;

import com.squareup.moshi.Json;
import tech.zolhungaj.amqapi.servercommands.Command;
import tech.zolhungaj.amqapi.servercommands.CommandType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public record NewQuestEvents(
        @Json(name = "events")
        List<EventsItem> quests
        ) implements Command {
    @Override
    public String commandName() {
        return CommandType.NEW_QUEST_EVENTS.commandName;
    }
    public record EventsItem(
            @Json(name = "questEvent")
            int questEventEnumId,
            Data data
    ){
        public QuestEventState questEventEnumValue(){
            return QuestEventState.forId(questEventEnumId());
        }
    }

    public enum QuestEventState{
        STATE_UPDATE(1);
        public final int id;

        private static final Map<Integer, QuestEventState> ID_MAP = new HashMap<>();
        static{
            for(QuestEventState qes : QuestEventState.values()){
                ID_MAP.put(qes.id, qes);
            }
        }

        QuestEventState(int id){
            this.id = id;
        }

        public static QuestEventState forId(int id){
            return ID_MAP.get(id);
        }
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

