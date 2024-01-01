package tech.zolhungaj.amqapi.servercommands.objects;

import com.squareup.moshi.Json;

public record ProgressBarState(
        @Json(name = "length") double totalLength,
        @Json(name = "played") double currentPosition
) {

}