package tech.zolhungaj.amqapi.servercommands.objects.messages;

public record Message(
    String sender,
    String message,
    boolean modMessage,
    boolean teamMessage,
    int messageId,
    MessageEmoji emojis,
    boolean atEveryone
){}
