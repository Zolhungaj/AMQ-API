package tech.zolhungaj.amqapi.commands.objects.messages;

import java.util.List;

public record MessageEmoji (
        List<Integer> emotes,
        List<CustomEmoji> customEmojis
){}
