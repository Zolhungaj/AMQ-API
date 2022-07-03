package tech.zolhungaj.amqapi.commands;

import tech.zolhungaj.amqapi.commands.objects.messages.DirectMessageEmojis;


/**
 * Event that happens when receiving a direct message
 */
public record DirectMessage(
        DirectMessageEmojis emojis,
        String sender,
        String message,
        Boolean modMessage
) implements Command{


    @Override
    public String getCommandName() {
        return CommandType.DIRECT_MESSAGE.commandName;
    }
}
