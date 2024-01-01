package tech.zolhungaj.amqapi.servercommands.globalstate;

import com.squareup.moshi.Json;
import tech.zolhungaj.amqapi.servercommands.CommandType;

@CommandType("server restart")
public record ServerRestartWarning(
        @Json(name = "msg")
        String message,
        @Json(name = "time")
        int timeInMinutes
){
}
