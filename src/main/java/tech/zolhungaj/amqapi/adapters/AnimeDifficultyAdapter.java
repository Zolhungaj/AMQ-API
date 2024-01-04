package tech.zolhungaj.amqapi.adapters;

import com.squareup.moshi.*;
import tech.zolhungaj.amqapi.servercommands.objects.AnimeDifficulty;

import java.io.IOException;

public class AnimeDifficultyAdapter extends JsonAdapter<AnimeDifficulty> {

    @Override
    @FromJson
    public AnimeDifficulty fromJson(JsonReader reader) throws IOException {
        JsonReader.Token token = reader.peek();
        return switch (token) {
            case NULL -> {
                reader.nextNull();
                yield null;
            }
            case STRING -> new AnimeDifficulty(null, reader.nextString());
            case NUMBER -> new AnimeDifficulty(reader.nextDouble(), null);
            default -> throw new JsonDataException("Expected String, null or number, but got a " + reader.peek().name());
        };
    }

    @Override
    @ToJson
    public void toJson(JsonWriter writer, AnimeDifficulty value) throws IOException {
        if(value == null){
            writer.nullValue();
        }else if(value.doubleValue() != null){
            writer.value(value.doubleValue());
        }else if(value.stringValue() != null){
            writer.value(value.stringValue());
        }else {
            writer.nullValue();
        }
    }
}
