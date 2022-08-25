package tech.zolhungaj.amqapi.servercommands;

import com.squareup.moshi.Json;

public record FriendOnlineChange (
        @Json(name = "name") String playerName,
        Boolean online
) implements Command{
    @Override
    public String getCommandName() {
        return CommandType.FRIEND_STATE_UPDATE.commandName;
    }
}
