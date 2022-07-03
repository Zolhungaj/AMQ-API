package tech.zolhungaj.amqapi.commands.objects.messages;

import java.util.List;

public record DirectMessageEmojis(
        List<String> shortCodes,
        List<Integer> emotes,
        List<CustomEmoji> customEmojis
) {
}
