package tech.zolhungaj.amqapi.servercommands.globalstate;

import com.squareup.moshi.Json;
import tech.zolhungaj.amqapi.servercommands.Command;
import tech.zolhungaj.amqapi.servercommands.CommandTypeOld;

public record FriendOnlineChange (
        @Json(name = "name") String playerName,
        Boolean online
) implements Command {
    @Override
    public String commandName() {
        return CommandTypeOld.FRIEND_STATE_UPDATE.commandName;
    }
}
