package tech.zolhungaj.amqapi.servercommands.globalstate;

import com.squareup.moshi.Json;
import tech.zolhungaj.amqapi.servercommands.CommandType;
import tech.zolhungaj.amqapi.servercommands.objects.PlayerGameState;

@CommandType("new friend")
public record FriendAdded(
        @Json(name = "name") String playerName,
        Boolean online,
        int status,
        PlayerGameState gameState
){}
