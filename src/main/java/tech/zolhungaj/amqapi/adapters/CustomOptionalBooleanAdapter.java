package tech.zolhungaj.amqapi.adapters;

import com.squareup.moshi.*;

import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

public class CustomOptionalBooleanAdapter extends JsonAdapter<Optional<Boolean>> {
    @Override
    @FromJson
    public Optional<Boolean> fromJson(JsonReader reader) throws IOException {
        return switch (reader.peek()) {
            case BOOLEAN -> Optional.of(reader.nextBoolean());
            case NUMBER -> {
                int i = reader.nextInt();
                if (i == 0) {
                    yield Optional.of(false);
                } else if (i == 1) {
                    yield Optional.of(true);
                } else {
                    throw new JsonDataException("Integer " + i + " is out of range [0-1]");
                }
            }
            case NULL -> {
                reader.nextNull();
                yield Optional.empty();
            }
            default -> throw new JsonDataException("Expected an Integer but got a " + reader.peek().name());
        };
    }

    @Override
    @ToJson
    public void toJson(JsonWriter writer, Optional<Boolean> value) throws IOException {
        if (Objects.requireNonNull(value).isPresent()) {
            writer.value(Boolean.TRUE.equals(value.get()) ? 1 : 0);
        } else {
            writer.nullValue();
        }
    }
}
