package tech.zolhungaj.amqapi.servercommands.gameroom;

import com.squareup.moshi.Json;
import tech.zolhungaj.amqapi.servercommands.CommandType;
import tech.zolhungaj.amqapi.servercommands.objects.messages.Badge;
import tech.zolhungaj.amqapi.servercommands.objects.messages.MessageEmoji;

import java.util.List;

@CommandType("Game Chat Message")
public record GameChatMessage(
    String sender,
    String message,
    @Json(name = "modMessage") Boolean isModMessage,
    @Json(name = "teamMessage") Boolean isTeamMessage,
    @Json(name = "nameGlow") Boolean hasNameGlow,
    @Json(name = "nameColor") Boolean nameColor,
    List<Badge> badges,
    int messageId,
    MessageEmoji emojis,
    @Json(name = "atEveryone") Boolean isAtEveryone
){}
