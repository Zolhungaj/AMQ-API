package tech.zolhungaj.amqapi.servercommands.objects.messages;

public record Message(
    String sender,
    String message,
    Boolean modMessage,
    Boolean teamMessage,
    Integer messageId,
    MessageEmoji emojis,
    Boolean atEveryone
){}
