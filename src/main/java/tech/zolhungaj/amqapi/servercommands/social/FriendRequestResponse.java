package tech.zolhungaj.amqapi.servercommands.social;

import com.squareup.moshi.Json;
import tech.zolhungaj.amqapi.servercommands.CommandType;

@CommandType("friend request")
public record FriendRequestResponse (
        @Json(name = "name") String playerName,
        String result,
        String reason
){}
