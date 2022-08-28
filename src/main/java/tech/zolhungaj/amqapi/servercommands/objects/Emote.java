package tech.zolhungaj.amqapi.servercommands.objects;

import com.squareup.moshi.Json;

public record Emote (
        Integer tierId,
        @Json(name = "name") String emoteName,
        Integer emoteId
){}
