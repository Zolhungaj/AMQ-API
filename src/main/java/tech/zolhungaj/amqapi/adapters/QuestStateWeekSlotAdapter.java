package tech.zolhungaj.amqapi.adapters;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.JsonReader;
import com.squareup.moshi.JsonWriter;
import tech.zolhungaj.amqapi.servercommands.objects.QuestStateWeekSlot;

import java.io.IOException;

public class QuestStateWeekSlotAdapter extends JsonAdapter<QuestStateWeekSlot> {

    @Override
    public QuestStateWeekSlot fromJson(JsonReader reader) throws IOException {
        return switch (reader.peek()) {
            case NUMBER -> {
                int intValue = reader.nextInt();
                yield switch (intValue) {
                    case 0 -> QuestStateWeekSlot.MONDAY;
                    case 1 -> QuestStateWeekSlot.TUESDAY;
                    case 2 -> QuestStateWeekSlot.WEDNESDAY;
                    case 3 -> QuestStateWeekSlot.THURSDAY;
                    case 4 -> QuestStateWeekSlot.FRIDAY;
                    case 5 -> QuestStateWeekSlot.SATURDAY;
                    case 6 -> QuestStateWeekSlot.SUNDAY;
                    default -> throw new IllegalStateException("Should be unreachable");
                };
            }
            case STRING -> {
                String stringValue = reader.nextString();
                yield switch (stringValue) {
                    case "event" -> QuestStateWeekSlot.EVENT;
                    default -> throw new IllegalStateException("Unknown string value: " + stringValue);
                };
            }
            default -> throw new IllegalStateException("Unexpected type: " + reader.peek().name());
        };
    }

    @Override
    public void toJson(JsonWriter writer, QuestStateWeekSlot value) throws IOException {
        if (value == null) {
            writer.nullValue();
        } else if (value == QuestStateWeekSlot.EVENT) {
            writer.value("event");
        } else {
            int intValue = switch (value) {
                case MONDAY -> 0;
                case TUESDAY -> 1;
                case WEDNESDAY -> 2;
                case THURSDAY -> 3;
                case FRIDAY -> 4;
                case SATURDAY -> 5;
                case SUNDAY -> 6;
                case EVENT -> throw new IllegalStateException("Should be unreachable");
            };
            writer.value(intValue);
        }
    }
}
