package tech.zolhungaj.amqapi.servercommands.social;

import tech.zolhungaj.amqapi.servercommands.Command;
import tech.zolhungaj.amqapi.servercommands.CommandTypeOld;
import tech.zolhungaj.amqapi.servercommands.objects.messages.DirectMessageEmojis;


/**
 * Event that happens when receiving a direct message
 */
public record DirectMessage(
        DirectMessageEmojis emojis,
        String sender,
        String message,
        Boolean modMessage
) implements Command {


    @Override
    public String commandName() {
        return CommandTypeOld.DIRECT_MESSAGE.commandName;
    }
}
