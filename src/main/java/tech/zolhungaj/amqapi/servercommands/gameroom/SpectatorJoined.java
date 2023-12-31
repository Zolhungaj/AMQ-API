package tech.zolhungaj.amqapi.servercommands.gameroom;

import com.squareup.moshi.Json;
import tech.zolhungaj.amqapi.servercommands.CommandType;

import java.util.Optional;

@CommandType("New Spectator")
public record SpectatorJoined(
        @Json(name = "name") String playerName,
        Optional<Integer> gamePlayerId //Usually null, might be set in ranked??
){}
