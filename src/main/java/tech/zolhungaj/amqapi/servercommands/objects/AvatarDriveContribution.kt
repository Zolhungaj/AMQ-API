package tech.zolhungaj.amqapi.servercommands.objects;

import com.squareup.moshi.Json;

public record AvatarDriveContribution(
    double amount,
    @Json(name = "name")
    String username
) {
}