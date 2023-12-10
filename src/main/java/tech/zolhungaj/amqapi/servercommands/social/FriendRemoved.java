package tech.zolhungaj.amqapi.servercommands.social;

import com.squareup.moshi.Json;
import tech.zolhungaj.amqapi.servercommands.Command;
import tech.zolhungaj.amqapi.servercommands.CommandTypeOld;

public record FriendRemoved(
        @Json(name = "name") String playerName
) implements Command {
    @Override
    public String commandName() {
        return CommandTypeOld.REMOVED_FRIEND.commandName;
    }
}
