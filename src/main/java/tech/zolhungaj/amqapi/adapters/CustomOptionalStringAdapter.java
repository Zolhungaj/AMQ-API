package tech.zolhungaj.amqapi.adapters;

import com.squareup.moshi.*;

import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

//uber cursed to fix the server sending a false boolean instead of a string in like one place
public class CustomOptionalStringAdapter extends JsonAdapter<Optional<String>> {
    @Override
    @FromJson
    public Optional<String> fromJson(JsonReader reader) throws IOException {
        return switch (reader.peek()) {
            case STRING -> Optional.of(reader.nextString());
            case NULL -> {
                reader.nextNull();
                yield Optional.empty();
            }
            case BOOLEAN -> {
                boolean cursedBoolean = reader.nextBoolean();
                if (!cursedBoolean) {
                    yield Optional.empty();
                }
                throw new JsonDataException("Expected a false but got a true");
            }
            default -> throw new JsonDataException("Expected a Boolean or String but got a " + reader.peek().name());
        };
    }

    @Override
    @ToJson
    public void toJson(JsonWriter writer, Optional<String> value) throws IOException {
        if (Objects.requireNonNull(value).isPresent()) {
            writer.value(value.get());
        } else {
            writer.nullValue();
        }
    }
}
