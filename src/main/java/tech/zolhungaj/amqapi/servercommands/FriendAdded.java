package tech.zolhungaj.amqapi.servercommands;

import com.squareup.moshi.Json;

public record FriendAdded(
        @Json(name = "name") String playerName,
        Boolean online,
        Integer status,
        Integer gameState
) implements Command{
    @Override
    public String getCommandName() {
        return CommandType.NEW_FRIEND.commandName;
    }
}
