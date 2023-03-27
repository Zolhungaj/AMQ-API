package tech.zolhungaj.amqapi.servercommands.gameroom;

import com.squareup.moshi.Json;
import tech.zolhungaj.amqapi.servercommands.Command;
import tech.zolhungaj.amqapi.servercommands.CommandType;
import tech.zolhungaj.amqapi.servercommands.objects.messages.Badge;
import tech.zolhungaj.amqapi.servercommands.objects.messages.MessageEmoji;

import java.util.List;

public record GameChatMessage(
    String sender,
    String message,
    @Json(name = "modMessage") boolean isModMessage,
    @Json(name = "teamMessage") boolean isTeamMessage,
    @Json(name = "nameGlow") boolean hasNameGlow,
    @Json(name = "nameColor") boolean nameColor,
    List<Badge> badges,
    int messageId,
    MessageEmoji emojis,
    @Json(name = "atEveryone") boolean isAtEveryone
) implements Command {
    @Override
    public String commandName() {
        return CommandType.SINGLE_CHAT_MESSAGE.commandName;
    }
}
