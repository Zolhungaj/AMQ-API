package tech.zolhungaj.amqapi.adapters;

import com.squareup.moshi.FromJson;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.JsonReader;
import com.squareup.moshi.JsonWriter;
import tech.zolhungaj.amqapi.servercommands.gameroom.PlayerLeft;
import tech.zolhungaj.amqapi.servercommands.objects.PlayerIdentifier;

import java.io.IOException;

public class PlayerLeftAdapter extends JsonAdapter<PlayerLeft> {
    @Override
    @FromJson
    public PlayerLeft fromJson(JsonReader jsonReader) throws IOException {
        Boolean kicked = null;
        Boolean disconnect = null;
        String newHost = null;
        PlayerIdentifier player = null;
        Integer readyPingCooldown = null;
        jsonReader.beginObject();
        while(jsonReader.peek() == JsonReader.Token.NAME) {
            String name = jsonReader.nextName();
            switch (name) {
                case "kicked" -> kicked = jsonReader.nextBoolean();
                case "disconnect" -> disconnect = jsonReader.nextBoolean();
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
                case "player" -> {
                    jsonReader.beginObject();
                    String playerName = null;
                    int gamePlayerId = -1;
                    while (jsonReader.peek() == JsonReader.Token.NAME) {
                        String playerFieldName = jsonReader.nextName();
                        if (playerFieldName.equals("name")) {
                            playerName = jsonReader.nextString();
                        } else if (playerFieldName.equals("gamePlayerId")) {
                            gamePlayerId = jsonReader.nextInt();
                        } else {
                            jsonReader.skipValue();
                        }
                    }
                    jsonReader.endObject();
                    if (playerName == null || gamePlayerId == -1) {
                        throw new IOException("Player name or game player id was null");
                    }
                    player = new PlayerIdentifier(playerName, gamePlayerId);
                }
                case "readyPingCooldown" -> readyPingCooldown = jsonReader.nextInt();
                default -> throw new IOException("Unexpected field: " + name);
            }
        }
        jsonReader.endObject();
        if(player == null){
            throw new IOException("player was null");
        }
        return new PlayerLeft(Boolean.TRUE.equals(kicked), Boolean.TRUE.equals(disconnect), newHost, player, readyPingCooldown);
    }

    @Override
    public void toJson(JsonWriter jsonWriter, PlayerLeft playerLeft) throws IOException {
        if(playerLeft == null) {
            jsonWriter.nullValue();
            return;
        }
        jsonWriter.beginObject();
        jsonWriter.name("kicked");
        jsonWriter.value(playerLeft.kicked());
        jsonWriter.name("disconnect");
        jsonWriter.value(playerLeft.disconnect());
        jsonWriter.name("newHost");
        jsonWriter.value(playerLeft.newHost());

        PlayerIdentifier playerIdentifier = playerLeft.player();
        jsonWriter.name("player");
        jsonWriter.beginObject();
        jsonWriter.name("name");
        jsonWriter.value(playerIdentifier.playerName());
        jsonWriter.name("gamePlayerId");
        jsonWriter.value(playerIdentifier.gamePlayerId());
        jsonWriter.endObject();

        jsonWriter.endObject();
    }
}
