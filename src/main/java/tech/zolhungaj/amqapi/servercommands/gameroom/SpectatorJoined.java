package tech.zolhungaj.amqapi.servercommands.gameroom;

import com.squareup.moshi.Json;
import tech.zolhungaj.amqapi.servercommands.Command;
import tech.zolhungaj.amqapi.servercommands.CommandType;
import java.util.Optional;

public record SpectatorJoined(
        @Json(name = "name") String playerName,
        Optional<Integer> gamePlayerId //Usually null, might be set in ranked??
) implements Command {
    @Override
    public String getCommandName() {
        return CommandType.NEW_SPECTATOR.commandName;
    }
}
