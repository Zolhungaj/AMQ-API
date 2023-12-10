package tech.zolhungaj.amqapi.servercommands.globalstate;

import com.squareup.moshi.Json;
import tech.zolhungaj.amqapi.servercommands.Command;
import tech.zolhungaj.amqapi.servercommands.CommandTypeOld;
import tech.zolhungaj.amqapi.servercommands.objects.PlayerGameState;

public record FriendAdded(
        @Json(name = "name") String playerName,
        Boolean online,
        int status,
        PlayerGameState gameState
) implements Command {
    @Override
    public String commandName() {
        return CommandTypeOld.NEW_FRIEND.commandName;
    }
}
