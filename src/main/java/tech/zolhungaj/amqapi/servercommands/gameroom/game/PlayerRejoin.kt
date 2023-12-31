package tech.zolhungaj.amqapi.servercommands.gameroom.game;

import com.squareup.moshi.Json;
import tech.zolhungaj.amqapi.servercommands.CommandType;

@CommandType("Rejoining Player")
public record PlayerRejoin(
        int gamePlayerId,
        @Json(name = "name")
        String playerName
){}
