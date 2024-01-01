package tech.zolhungaj.amqapi.adapters;

import com.squareup.moshi.FromJson;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.JsonReader;
import com.squareup.moshi.JsonWriter;
import tech.zolhungaj.amqapi.servercommands.gameroom.SpectatorLeft;

import java.io.IOException;

public class SpectatorLeftAdapter extends JsonAdapter<SpectatorLeft> {
    @Override
    @FromJson
    public SpectatorLeft fromJson(JsonReader jsonReader) throws IOException {
        Boolean kicked = null;
        String newHost = null;
        String spectator = null;
        jsonReader.beginObject();
        while(jsonReader.peek() == JsonReader.Token.NAME) {
            String name = jsonReader.nextName();
            switch (name) {
                case "kicked" -> kicked = switch (jsonReader.peek()){
                    case BOOLEAN -> jsonReader.nextBoolean();
                    case NULL -> {
                        jsonReader.nextNull();
                        yield null;
                    }
                    default -> throw new IOException("Expected a boolean or null but got a " + jsonReader.peek().name());
                };
                case "newHost" -> newHost = switch (jsonReader.peek()) {
                    case NULL -> {
                        jsonReader.nextNull();
                        yield null;
                    }
                    case STRING -> jsonReader.nextString();
                    case BOOLEAN -> {
                        boolean cursedBoolean = jsonReader.nextBoolean();
                        if (!cursedBoolean) {
                            yield null;
                        }
                        throw new IOException("Expected a false but got a true");
                    }
                    default -> throw new IOException("Expected a String, false or null but got a " + jsonReader.peek().name());
                };
                case "spectator" -> spectator = jsonReader.nextString();
                default -> throw new IOException("Unexpected field: " + name);
            }
        }
        jsonReader.endObject();
        if(spectator == null){
            throw new IOException("spectator was null");
        }
        return new SpectatorLeft(Boolean.TRUE.equals(kicked), newHost, spectator);
    }

    @Override
    public void toJson(JsonWriter jsonWriter, SpectatorLeft spectatorLeft) throws IOException {
        if(spectatorLeft == null) {
            jsonWriter.nullValue();
            return;
        }
        jsonWriter.beginObject();
        jsonWriter.name("kicked");
        jsonWriter.value(spectatorLeft.kicked());
        jsonWriter.name("newHost");
        jsonWriter.value(spectatorLeft.newHost());
        jsonWriter.name("spectator");
        jsonWriter.value(spectatorLeft.playerName());
        jsonWriter.endObject();
    }
}
