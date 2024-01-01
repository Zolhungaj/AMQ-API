package tech.zolhungaj.amqapi.servercommands.social;

import tech.zolhungaj.amqapi.servercommands.CommandType;
import tech.zolhungaj.amqapi.servercommands.objects.messages.DirectMessageEmojis;


/**
 * Event that happens when receiving a direct message
 */
@CommandType("chat message")
public record DirectMessage(
        DirectMessageEmojis emojis,
        String sender,
        String message,
        Boolean modMessage
){}
