package tech.zolhungaj.amqapi.commands;

import java.util.List;

public record Buble(
        List<String> emoteIds,
        List<String> shortCodes
) {
}
