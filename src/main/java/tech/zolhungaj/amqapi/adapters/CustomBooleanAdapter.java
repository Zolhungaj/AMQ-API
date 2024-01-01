package tech.zolhungaj.amqapi.adapters;

import com.squareup.moshi.*;

import java.io.IOException;

public class CustomBooleanAdapter extends JsonAdapter<Boolean> {
    @Override
    @FromJson
    public Boolean fromJson(JsonReader reader) throws IOException {
        return switch (reader.peek()) {
            case BOOLEAN -> reader.nextBoolean();
            case NUMBER -> {
                int i = reader.nextInt();
                if (i == 0) {
                    yield false;
                } else if (i == 1) {
                    yield true;
                } else {
                    throw new JsonDataException("Integer " + i + " is out of range [0-1]");
                }
            }
            case NULL -> {
                reader.nextNull();
                yield false;
            }
            default -> throw new JsonDataException("Expected an Integer but got a " + reader.peek().name());
        };
    }

    @Override
    @ToJson
    public void toJson(JsonWriter writer, Boolean value) throws IOException {
        writer.value(Boolean.TRUE.equals(value) ? 1 : 0);
    }
}
