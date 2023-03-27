package tech.zolhungaj.amqapi.servercommands.globalstate;

import com.squareup.moshi.Json;
import tech.zolhungaj.amqapi.servercommands.Command;
import tech.zolhungaj.amqapi.servercommands.CommandType;

public record FriendOnlineChange (
        @Json(name = "name") String playerName,
        boolean online
) implements Command {
    @Override
    public String commandName() {
        return CommandType.FRIEND_STATE_UPDATE.commandName;
    }
}
