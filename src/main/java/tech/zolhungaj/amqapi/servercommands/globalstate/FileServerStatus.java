package tech.zolhungaj.amqapi.servercommands.globalstate;

import com.squareup.moshi.Json;
import tech.zolhungaj.amqapi.servercommands.CommandType;

@CommandType("server state change")
public record FileServerStatus(
        @Json(name = "name")
        String serverName,
        boolean online
){}
