package tech.zolhungaj.amqapi.servercommands.objects;

import com.squareup.moshi.Json;

public record Emote (
        int tierId,
        @Json(name = "name") String emoteName,
        int emoteId
){}
