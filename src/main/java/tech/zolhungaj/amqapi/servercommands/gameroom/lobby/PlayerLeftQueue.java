package tech.zolhungaj.amqapi.servercommands.gameroom.lobby;

import com.squareup.moshi.Json;
import tech.zolhungaj.amqapi.servercommands.CommandType;

@CommandType("player left queue")
public record PlayerLeftQueue(
        @Json(name = "name") String playerName
){}
