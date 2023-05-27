package tech.zolhungaj.amqapi.servercommands.gameroom.game;

import com.squareup.moshi.Json;
import tech.zolhungaj.amqapi.servercommands.Command;
import tech.zolhungaj.amqapi.servercommands.CommandType;

import java.util.List;

public record PlayersAnswered(
        @Json(name = "array") //from Client.pollCommand
        List<Integer> gamePlayerIds
) implements Command {
    @Override
    public String commandName() {
        return CommandType.PLAYER_ANSWERED.commandName;
    }
}
