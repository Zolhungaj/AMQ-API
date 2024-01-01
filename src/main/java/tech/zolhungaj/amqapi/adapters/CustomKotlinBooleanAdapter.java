package tech.zolhungaj.amqapi.adapters;

import com.squareup.moshi.FromJson;
import com.squareup.moshi.JsonReader;
import com.squareup.moshi.JsonWriter;
import com.squareup.moshi.ToJson;

import java.io.IOException;

public class CustomKotlinBooleanAdapter {
    private static final CustomBooleanAdapter HELPER = new CustomBooleanAdapter();

    @FromJson
    boolean fromJson(JsonReader reader) throws IOException {
        Boolean value = HELPER.fromJson(reader);
        return Boolean.TRUE.equals(value); // null coalesces into false
    }

    @ToJson
    public void toJson(JsonWriter writer, boolean value) throws IOException {
        HELPER.toJson(writer, value);
    }
}
