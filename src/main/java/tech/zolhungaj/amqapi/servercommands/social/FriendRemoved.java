package tech.zolhungaj.amqapi.servercommands.social;

import com.squareup.moshi.Json;
import tech.zolhungaj.amqapi.servercommands.CommandType;

@CommandType("friend removed")
public record FriendRemoved(
        @Json(name = "name") String playerName
){}
