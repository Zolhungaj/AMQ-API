package tech.zolhungaj.amqapi.servercommands.objects;

import com.squareup.moshi.Json;

public record CumulativeAvatarDonation(
    double amount,
    @Json(name = "name")
    String avatarName
) {
}