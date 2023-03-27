package tech.zolhungaj.amqapi.servercommands.globalstate;

import com.squareup.moshi.Json;
import tech.zolhungaj.amqapi.servercommands.Command;
import tech.zolhungaj.amqapi.servercommands.CommandType;

public record FileServerStateChange(
        @Json(name = "name")
        String serverName,
        Boolean online
        ) implements Command {
    @Override
    public String commandName() {
        return CommandType.FILE_SERVER_STATE_CHANGE.commandName;
    }
}
