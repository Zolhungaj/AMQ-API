package tech.zolhungaj.amqapi.commands.objects.messages;

import java.util.List;

public record Message(
    String sender,
    String message,
    boolean modMessage,
    boolean teamMessage,
    int messageId,
    MessageEmoji emojis,
    boolean atEveryone
){}
