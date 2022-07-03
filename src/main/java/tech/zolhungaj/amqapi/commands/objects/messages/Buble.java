package tech.zolhungaj.amqapi.commands.objects.messages;

import java.util.List;

public record Buble(
        List<String> emoteIds,
        List<String> shortCodes
) {
}
