package tech.zolhungaj.amqapi.servercommands.globalstate;

import com.squareup.moshi.Json;
import tech.zolhungaj.amqapi.servercommands.Command;
import tech.zolhungaj.amqapi.servercommands.CommandTypeOld;

public record FileServerStatus(
        @Json(name = "name")
        String serverName,
        boolean online
        ) implements Command {
    @Override
    public String commandName() {
        return CommandTypeOld.FILE_SERVER_STATE_CHANGE.commandName;
    }
}
