package tech.zolhungaj.amqapi.servercommands.social;

import tech.zolhungaj.amqapi.servercommands.Command;
import tech.zolhungaj.amqapi.servercommands.CommandType;
import tech.zolhungaj.amqapi.servercommands.objects.messages.Badge;
import tech.zolhungaj.amqapi.servercommands.objects.messages.MessageEmoji;

import java.util.List;

public record GameChatMessage(
    String sender,
    String message,
    Boolean modMessage,
    Boolean teamMessage,
    List<Badge> badges,
    Integer messageId,
    MessageEmoji emojis,
    Boolean atEveryone
) implements Command {
    @Override
    public String getCommandName() {
        return CommandType.SINGLE_CHAT_MESSAGE.commandName;
    }
}
