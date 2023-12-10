package tech.zolhungaj.amqapi.servercommands.gameroom.game;

import com.squareup.moshi.Json;
import tech.zolhungaj.amqapi.servercommands.CommandType;

@CommandType("quiz skip message")
public record QuizSkipMessage(
        @Json(name = "string") //this is from the hack in Client.pollCommand
        String message
){}
