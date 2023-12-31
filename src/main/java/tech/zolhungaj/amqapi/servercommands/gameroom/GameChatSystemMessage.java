package tech.zolhungaj.amqapi.servercommands.gameroom;

import com.squareup.moshi.Json;
import tech.zolhungaj.amqapi.servercommands.CommandType;

import java.util.Optional;

@CommandType("Game System Chat Message")
public record GameChatSystemMessage(
    String title,
    @Json(name = "msg") Optional<String> message,
    @Json(name = "teamMessage") Boolean isTeamMessage
){}
