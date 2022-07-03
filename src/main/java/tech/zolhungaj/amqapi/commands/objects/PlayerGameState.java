package tech.zolhungaj.amqapi.commands.objects;

import com.squareup.moshi.Json;

public record PlayerGameState (
        Integer gameId,
        Boolean isSpectator,
        @Json(name = "private") Boolean isPrivateRoom,
        Boolean inLobby
){}
