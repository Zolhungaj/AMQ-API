package tech.zolhungaj.amqapi.servercommands.objects;

import com.squareup.moshi.Json;

public record NexusBuff(

    @Json(name = "fileName")
    String filename,

    @Json(name = "debuff")
    Boolean isDebuff,

    @Json(name = "name")
    String buffName,

    String description
) {}