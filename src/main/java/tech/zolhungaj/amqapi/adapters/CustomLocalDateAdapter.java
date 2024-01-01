package tech.zolhungaj.amqapi.adapters;

import com.squareup.moshi.*;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CustomLocalDateAdapter extends JsonAdapter<LocalDate> {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    @Override
    @FromJson
    public LocalDate fromJson(JsonReader reader) throws IOException {
        if (reader.peek() == JsonReader.Token.STRING) {
            return LocalDate.parse(reader.nextString(), FORMATTER);
        } else {
            throw new JsonDataException("Expected an String, but got a " + reader.peek().name());
        }
    }

    @Override
    @ToJson
    public void toJson(JsonWriter writer, LocalDate value) throws IOException {
        writer.value(FORMATTER.format(value));
    }
}
