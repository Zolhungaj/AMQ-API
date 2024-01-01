package tech.zolhungaj.amqapi.servercommands.objects;

import com.squareup.moshi.Json;

import java.util.Optional;

public record PlayerIdentifier(
        @Json(name = "name") String playerName,
        Optional<Integer> gamePlayerId
) {
}
