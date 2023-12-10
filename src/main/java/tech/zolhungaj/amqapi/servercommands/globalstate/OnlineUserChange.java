package tech.zolhungaj.amqapi.servercommands.globalstate;

import com.squareup.moshi.Json;
import tech.zolhungaj.amqapi.servercommands.Command;
import tech.zolhungaj.amqapi.servercommands.CommandTypeOld;

public record OnlineUserChange(
    @Json(name = "name")
    String username,
    Boolean online
) implements Command {
    @Override
    public String commandName() {
        return CommandTypeOld.ONLINE_USER_CHANGE.commandName;
    }
}