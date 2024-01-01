package tech.zolhungaj.amqapi.servercommands.gameroom.lobby;

import com.squareup.moshi.Json;
import tech.zolhungaj.amqapi.servercommands.CommandType;

@CommandType("new player in game queue")
public record PlayerJoinedQueue(
        @Json(name = "name") String playerName
){}
