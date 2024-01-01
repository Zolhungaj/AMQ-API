package tech.zolhungaj.amqapi.adapters;

import com.squareup.moshi.*;

import java.io.IOException;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

public class CustomOffsetDateTimeAdapter extends JsonAdapter<OffsetDateTime> {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ISO_OFFSET_DATE_TIME;

    @Override
    @FromJson
    public OffsetDateTime fromJson(JsonReader reader) throws IOException {
        if (reader.peek() == JsonReader.Token.STRING) {
            return OffsetDateTime.parse(reader.nextString(), FORMATTER);
        } else {
            throw new JsonDataException("Expected an String, but got a " + reader.peek().name());
        }
    }

    @Override
    @ToJson
    public void toJson(JsonWriter writer, OffsetDateTime value) throws IOException {
        writer.value(FORMATTER.format(value));
    }
}
