package tech.zolhungaj.amqapi.servercommands;

import com.squareup.moshi.Json;

public record FriendRequestReceived(
        @Json(name = "name") String playerName
) implements Command{
    @Override
    public String getCommandName() {
        return CommandType.FRIEND_REQUEST.commandName;
    }
}
