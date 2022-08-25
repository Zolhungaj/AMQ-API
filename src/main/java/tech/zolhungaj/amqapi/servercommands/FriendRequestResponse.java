package tech.zolhungaj.amqapi.servercommands;

import com.squareup.moshi.Json;

public record FriendRequestResponse (
        @Json(name = "name") String playerName,
        String result,
        String reason
) implements Command{
    @Override
    public String getCommandName() {
        return CommandType.FRIEND_REQUEST_ACKNOWLEDGEMENT.commandName;
    }
}
