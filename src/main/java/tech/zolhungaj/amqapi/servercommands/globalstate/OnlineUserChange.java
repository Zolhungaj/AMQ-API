package tech.zolhungaj.amqapi.servercommands.globalstate;

import com.squareup.moshi.Json;
import tech.zolhungaj.amqapi.servercommands.Command;
import tech.zolhungaj.amqapi.servercommands.CommandType;

public record OnlineUserChange(
    @Json(name = "name")
    String username,
    boolean online
) implements Command {
    @Override
    public String getCommandName() {
        return CommandType.ONLINE_USER_CHANGE.commandName;
    }
}