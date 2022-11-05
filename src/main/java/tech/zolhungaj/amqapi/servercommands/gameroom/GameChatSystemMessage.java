package tech.zolhungaj.amqapi.servercommands.gameroom;

import com.squareup.moshi.Json;
import tech.zolhungaj.amqapi.servercommands.Command;
import tech.zolhungaj.amqapi.servercommands.CommandType;

import java.util.Optional;

public record GameChatSystemMessage(
    String title,
    @Json(name = "msg") Optional<String> message,
    @Json(name = "teamMessage") Boolean isTeamMessage
) implements Command {
    @Override
    public String getCommandName() {
        return CommandType.SYSTEM_CHAT_MESSAGE.commandName;
    }
}