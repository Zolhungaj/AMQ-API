package tech.zolhungaj.amqapi.servercommands.globalstate;

import com.squareup.moshi.Json;
import tech.zolhungaj.amqapi.servercommands.Command;
import tech.zolhungaj.amqapi.servercommands.CommandType;

public record Alert(
        String title,
        String message,
        @Json(name = "easyClose")
        boolean allowOutsideClickToClose
) implements Command {
    @Override
    public String commandName() {
        return CommandType.ALERT.commandName;
    }
}
