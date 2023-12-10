package tech.zolhungaj.amqapi.servercommands.globalstate;

import com.squareup.moshi.Json;
import tech.zolhungaj.amqapi.servercommands.Command;
import tech.zolhungaj.amqapi.servercommands.CommandTypeOld;

import java.util.List;

public record AllOnlineUsers(
        @Json(name = "array")
        List<String> list
        ) implements Command {
    @Override
    public String commandName() {
        return CommandTypeOld.ALL_ONLINE_USERS.commandName;
    }
}
