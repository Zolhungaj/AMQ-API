package tech.zolhungaj.amqapi.commands;

import java.util.List;

public record GameChatUpdate(
        List<Message> messages,
        List<Buble> bubles
) implements Command{
    @Override
    public String getName() {
        return Commands.CHAT_MESSAGES.name();
    }
}
