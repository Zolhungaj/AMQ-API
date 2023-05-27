package tech.zolhungaj.amqapi.servercommands.gameroom;

import com.squareup.moshi.Json;
import tech.zolhungaj.amqapi.servercommands.Command;
import tech.zolhungaj.amqapi.servercommands.CommandType;

import java.util.Optional;

public record SpectatorLeft(
        Optional<Boolean> kicked,
        Optional<String> newHost,
        @Json(name = "spectator") String playerName
) implements Command {
    public SpectatorLeft{
        if(newHost == null) newHost = Optional.empty();
    }
    @Override
    public String commandName() {
        return CommandType.SPECTATOR_LEFT.commandName;
    }
}
