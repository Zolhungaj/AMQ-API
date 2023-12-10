package tech.zolhungaj.amqapi.servercommands.gameroom.game;

import com.squareup.moshi.Json;
import tech.zolhungaj.amqapi.servercommands.CommandType;

@CommandType("quiz waiting buffering")
public record WaitingForBuffering(
        @Json(name = "firstSong")
        boolean isFirstSong
){}
