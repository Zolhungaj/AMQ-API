package tech.zolhungaj.amqapi.servercommands.gameroom;

import com.squareup.moshi.Json;
import tech.zolhungaj.amqapi.servercommands.Command;
import tech.zolhungaj.amqapi.servercommands.CommandType;

public record PlayerRejoin(
        int gamePlayerId,
        @Json(name = "name")
        String playerName
) implements Command {
    @Override
    public String commandName() {
        return CommandType.PLAYER_REJOIN.commandName;
    }
}
