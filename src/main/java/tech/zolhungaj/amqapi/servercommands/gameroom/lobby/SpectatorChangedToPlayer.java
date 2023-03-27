package tech.zolhungaj.amqapi.servercommands.gameroom.lobby;

import com.squareup.moshi.Json;
import tech.zolhungaj.amqapi.servercommands.Command;
import tech.zolhungaj.amqapi.servercommands.CommandType;
import tech.zolhungaj.amqapi.servercommands.objects.PlayerAvatar;

import java.util.Optional;

public record SpectatorChangedToPlayer(
        @Json(name = "name")
        String playerName,
        int gamePlayerId,
        int level,
        PlayerAvatar avatar,
        boolean ready,
        boolean inGame,
        Optional<Integer> teamNumber
) implements Command {
    @Override
    public String commandName() {
        return CommandType.SPECTATOR_CHANGED_TO_PLAYER.commandName;
    }
}
