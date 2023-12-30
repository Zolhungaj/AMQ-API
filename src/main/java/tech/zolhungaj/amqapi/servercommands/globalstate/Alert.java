package tech.zolhungaj.amqapi.servercommands.globalstate;

import com.squareup.moshi.Json;
import tech.zolhungaj.amqapi.servercommands.CommandType;

@CommandType("alert")
public record Alert(
        String title,
        String message,
        @Json(name = "easyClose")
        boolean allowOutsideClickToClose
){}
