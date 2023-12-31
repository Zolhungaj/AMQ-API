package tech.zolhungaj.amqapi.servercommands.gameroom;

import com.squareup.moshi.Json;
import tech.zolhungaj.amqapi.servercommands.CommandType;

import java.util.Optional;

@CommandType("Spectator Left")
public record SpectatorLeft(
        Optional<Boolean> kicked,
        Optional<String> newHost,
        @Json(name = "spectator") String playerName
){
    public SpectatorLeft{
        if(newHost == null) newHost = Optional.empty();
    }
}
