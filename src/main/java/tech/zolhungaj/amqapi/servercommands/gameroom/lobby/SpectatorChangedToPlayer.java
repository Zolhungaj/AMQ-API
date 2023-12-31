package tech.zolhungaj.amqapi.servercommands.gameroom.lobby;

import com.squareup.moshi.Json;
import tech.zolhungaj.amqapi.servercommands.CommandType;
import tech.zolhungaj.amqapi.servercommands.objects.PlayerAvatar;

import java.util.Optional;

@CommandType("Spectator Change To Player")
public record SpectatorChangedToPlayer(
        @Json(name = "name")
        String playerName,
        int gamePlayerId,
        int level,
        PlayerAvatar avatar,
        Boolean ready,
        Boolean inGame,
        Optional<Integer> teamNumber
){}
