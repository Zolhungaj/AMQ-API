package tech.zolhungaj.amqapi.servercommands.social;

import tech.zolhungaj.amqapi.servercommands.Command;
import tech.zolhungaj.amqapi.servercommands.CommandType;
import tech.zolhungaj.amqapi.servercommands.objects.messages.Buble;
import tech.zolhungaj.amqapi.servercommands.objects.messages.Message;

import java.util.List;

public record GameChatUpdate(
        List<Message> messages,
        List<Buble> bubles
) implements Command {
    @Override
    public String getCommandName() {
        return CommandType.CHAT_MESSAGES.commandName;
    }
}
