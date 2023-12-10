package tech.zolhungaj.amqapi.servercommands.gameroom;

import com.squareup.moshi.Json;
import tech.zolhungaj.amqapi.servercommands.Command;
import tech.zolhungaj.amqapi.servercommands.CommandTypeOld;

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
        return CommandTypeOld.SPECTATOR_LEFT.commandName;
    }
}
