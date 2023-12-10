package tech.zolhungaj.amqapi.servercommands.gameroom.game;

import com.squareup.moshi.Json;
import tech.zolhungaj.amqapi.servercommands.Command;
import tech.zolhungaj.amqapi.servercommands.CommandTypeOld;

import java.util.List;

public record PlayersAnswered(
        @Json(name = "array") //from Client.pollCommand
        List<Integer> gamePlayerIds
) implements Command {
    @Override
    public String commandName() {
        return CommandTypeOld.PLAYER_ANSWERED.commandName;
    }
}
