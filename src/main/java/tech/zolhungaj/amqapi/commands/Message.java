package tech.zolhungaj.amqapi.commands;

import java.util.List;

public record Message(
    String sender,
    String message,
    boolean modMessage,
    boolean teamMessage,
    int messageId,
    MessageEmoji emojis,
    boolean atEveryone
){
    public record MessageEmoji (
            List<Integer> emotes,
            List<CustomEmoji> customEmojis
    ){
        public record CustomEmoji(String id, String name){}
    }
}
