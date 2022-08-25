package tech.zolhungaj.amqapi.servercommands;

import com.squareup.moshi.Json;

public record FriendRemoved(
        @Json(name = "name") String playerName
) implements Command{
    @Override
    public String getCommandName() {
        return CommandType.REMOVED_FRIEND.commandName;
    }
}
