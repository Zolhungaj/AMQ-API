package tech.zolhungaj.amqapi.servercommands.globalstate;

import com.squareup.moshi.Json;
import tech.zolhungaj.amqapi.servercommands.CommandType;

@CommandType("friend state change")
public record FriendOnlineChange (
        @Json(name = "name") String playerName,
        Boolean online
){}
