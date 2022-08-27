package tech.zolhungaj.amqapi.servercommands;

import com.squareup.moshi.Json;
import tech.zolhungaj.amqapi.servercommands.objects.PlayerGameState;

public record FriendAdded(
        @Json(name = "name") String playerName,
        Boolean online,
        Integer status,
        PlayerGameState gameState
) implements Command{
    @Override
    public String getCommandName() {
        return CommandType.NEW_FRIEND.commandName;
    }
}
