package tech.zolhungaj.amqapi.servercommands.objects;

import com.squareup.moshi.Json;

public record TopAvatarDonation(
    @Json(name = "name")
    String avatarName,
    @Json(name = "value")
    double amount
) {
}