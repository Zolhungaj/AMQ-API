package tech.zolhungaj.amqapi.servercommands.social;

import com.squareup.moshi.Json;
import tech.zolhungaj.amqapi.servercommands.CommandType;
import tech.zolhungaj.amqapi.servercommands.objects.PlayerGameState;
import tech.zolhungaj.amqapi.servercommands.objects.PlayerStatus;

import java.util.Optional;

@CommandType("friend social status change")
public record FriendSocialStatusUpdate(
        String name,
        @Json(name = "socialStatus") PlayerStatus status,
        Optional<PlayerGameState> gameState
){}
