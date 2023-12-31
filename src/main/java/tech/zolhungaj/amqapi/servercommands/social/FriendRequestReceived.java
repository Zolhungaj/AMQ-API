package tech.zolhungaj.amqapi.servercommands.social;

import com.squareup.moshi.Json;
import tech.zolhungaj.amqapi.servercommands.CommandType;

@CommandType("new friend request recived")//sic
public record FriendRequestReceived(
        @Json(name = "name") String playerName
){}
