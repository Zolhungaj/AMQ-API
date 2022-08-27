package tech.zolhungaj.amqapi.servercommands.globalstate;

import com.squareup.moshi.Json;
import tech.zolhungaj.amqapi.servercommands.Command;
import tech.zolhungaj.amqapi.servercommands.CommandType;

public record FriendOnlineChange (
        @Json(name = "name") String playerName,
        Boolean online
) implements Command {
    @Override
    public String getCommandName() {
        return CommandType.FRIEND_STATE_UPDATE.commandName;
    }
}
