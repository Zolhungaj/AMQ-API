package tech.zolhungaj.amqapi.servercommands.gameroom.game;

import com.squareup.moshi.Json;
import tech.zolhungaj.amqapi.servercommands.Command;
import tech.zolhungaj.amqapi.servercommands.CommandTypeOld;

public record PlayerRejoin(
        int gamePlayerId,
        @Json(name = "name")
        String playerName
) implements Command {
    @Override
    public String commandName() {
        return CommandTypeOld.PLAYER_REJOIN.commandName;
    }
}
