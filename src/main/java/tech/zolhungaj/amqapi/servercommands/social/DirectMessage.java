package tech.zolhungaj.amqapi.servercommands.social;

import tech.zolhungaj.amqapi.servercommands.Command;
import tech.zolhungaj.amqapi.servercommands.CommandType;
import tech.zolhungaj.amqapi.servercommands.objects.messages.DirectMessageEmojis;


/**
 * Event that happens when receiving a direct message
 */
public record DirectMessage(
        DirectMessageEmojis emojis,
        String sender,
        String message,
        boolean modMessage
) implements Command {


    @Override
    public String commandName() {
        return CommandType.DIRECT_MESSAGE.commandName;
    }
}
