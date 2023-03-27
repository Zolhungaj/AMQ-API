package tech.zolhungaj.amqapi.servercommands.objects;

import com.squareup.moshi.Json;

import java.util.Optional;

public record Player(
        @Json(name = "name")
        String playerName,
        int gamePlayerId,
        int level,
        PlayerAvatar avatar,
        Boolean ready,
        Boolean inGame,
        Optional<Integer> teamNumber
) {}
