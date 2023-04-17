package tech.zolhungaj.amqapi.servercommands.globalstate;

import com.squareup.moshi.Json;
import tech.zolhungaj.amqapi.servercommands.Command;
import tech.zolhungaj.amqapi.servercommands.CommandType;

public record HtmlAlert(
        String title,
        String message,
        @Json(name = "storeTransactionTrigger")
        boolean isFromStoreTransactionTrigger
) implements Command {
    @Override
    public String commandName() {
        return CommandType.HTML_ALERT.commandName;
    }
}