package tech.zolhungaj.amqapi.servercommands.globalstate;

import com.squareup.moshi.Json;
import tech.zolhungaj.amqapi.servercommands.CommandType;

import java.util.List;

@CommandType("all online users")
public record AllOnlineUsers(
        @Json(name = "array")
        List<String> list
){}
