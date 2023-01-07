package tech.zolhungaj.amqapi.servercommands.globalstate;

import com.squareup.moshi.Json;
import tech.zolhungaj.amqapi.servercommands.Command;
import tech.zolhungaj.amqapi.servercommands.CommandType;

public record ServerRestartWarning(
        @Json(name = "msg")
        String message,
        @Json(name = "time")
        int timeInMinutes
        ) implements Command {
    @Override
    public String commandName() {
        return CommandType.SERVER_RESTART.commandName;
    }
}
