package tech.zolhungaj.amqapi.servercommands.globalstate;

import com.squareup.moshi.Json;
import tech.zolhungaj.amqapi.servercommands.CommandType;

@CommandType("online user change")
public record OnlineUserChange(
    @Json(name = "name")
    String username,
    Boolean online
){}