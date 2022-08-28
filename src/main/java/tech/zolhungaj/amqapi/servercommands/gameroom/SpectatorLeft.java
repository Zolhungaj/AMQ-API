package tech.zolhungaj.amqapi.servercommands.gameroom;

import com.squareup.moshi.Json;
import tech.zolhungaj.amqapi.servercommands.Command;
import tech.zolhungaj.amqapi.servercommands.CommandType;

import java.util.Optional;

public record SpectatorLeft(
        Optional<Boolean> kicked,
        String newHost, //TODO: test behaviour, this is either a boolean false or a string ...
        @Json(name = "spectator") String playerName
) implements Command {
    @Override
    public String getCommandName() {
        return CommandType.SPECTATOR_LEFT.commandName;
    }
}
