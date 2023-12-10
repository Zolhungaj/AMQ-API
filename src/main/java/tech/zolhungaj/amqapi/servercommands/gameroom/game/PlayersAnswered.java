package tech.zolhungaj.amqapi.servercommands.gameroom.game;

import com.squareup.moshi.Json;
import tech.zolhungaj.amqapi.servercommands.CommandType;

import java.util.List;

@CommandType("player answered")
public record PlayersAnswered(
        @Json(name = "array") //from Client.pollCommand
        List<Integer> gamePlayerIds
){}
