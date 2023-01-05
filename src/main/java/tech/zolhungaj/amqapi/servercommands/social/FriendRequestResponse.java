package tech.zolhungaj.amqapi.servercommands.social;

import com.squareup.moshi.Json;
import tech.zolhungaj.amqapi.servercommands.Command;
import tech.zolhungaj.amqapi.servercommands.CommandType;

public record FriendRequestResponse (
        @Json(name = "name") String playerName,
        String result,
        String reason
) implements Command {
    @Override
    public String commandName() {
        return CommandType.FRIEND_REQUEST_ACKNOWLEDGEMENT.commandName;
    }
}
