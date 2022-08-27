package tech.zolhungaj.amqapi.servercommands.objects.messages;

import com.squareup.moshi.Json;

public record Badge(
        @Json(name = "fileName") String filename,
        @Json(name = "name") String badgeName
) {
}
