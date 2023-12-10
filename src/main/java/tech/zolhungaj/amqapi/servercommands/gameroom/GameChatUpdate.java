package tech.zolhungaj.amqapi.servercommands.gameroom;

import tech.zolhungaj.amqapi.servercommands.Command;
import tech.zolhungaj.amqapi.servercommands.CommandTypeOld;
import tech.zolhungaj.amqapi.servercommands.objects.messages.Buble;

import java.util.List;

public record GameChatUpdate(
        List<GameChatMessage> messages,
        List<Buble> bubles
) implements Command {
    @Override
    public String commandName() {
        return CommandTypeOld.CHAT_MESSAGES.commandName;
    }
}
