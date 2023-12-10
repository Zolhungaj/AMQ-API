package tech.zolhungaj.amqapi.servercommands.gameroom.lobby;

import com.squareup.moshi.Json;
import tech.zolhungaj.amqapi.servercommands.Command;
import tech.zolhungaj.amqapi.servercommands.CommandTypeOld;
import tech.zolhungaj.amqapi.servercommands.objects.PlayerAvatar;

import java.util.Optional;

public record SpectatorChangedToPlayer(
        @Json(name = "name")
        String playerName,
        int gamePlayerId,
        int level,
        PlayerAvatar avatar,
        Boolean ready,
        Boolean inGame,
        Optional<Integer> teamNumber
) implements Command {
    @Override
    public String commandName() {
        return CommandTypeOld.SPECTATOR_CHANGED_TO_PLAYER.commandName;
    }
}
