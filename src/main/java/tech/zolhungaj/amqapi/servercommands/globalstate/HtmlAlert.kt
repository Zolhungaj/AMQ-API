package tech.zolhungaj.amqapi.servercommands.globalstate;

import com.squareup.moshi.Json;
import tech.zolhungaj.amqapi.servercommands.CommandType;

@CommandType("html alert")
public record HtmlAlert(
        String title,
        String message,
        @Json(name = "storeTransactionTrigger")
        boolean isFromStoreTransactionTrigger
){}
