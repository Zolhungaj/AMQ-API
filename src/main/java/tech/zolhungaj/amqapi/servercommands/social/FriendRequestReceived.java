package tech.zolhungaj.amqapi.servercommands.social;

import com.squareup.moshi.Json;
import tech.zolhungaj.amqapi.servercommands.Command;
import tech.zolhungaj.amqapi.servercommands.CommandType;

public record FriendRequestReceived(
        @Json(name = "name") String playerName
) implements Command {
    @Override
    public String getCommandName() {
        return CommandType.FRIEND_REQUEST.commandName;
    }
}
