package tech.zolhungaj.amqapi.servercommands.gameroom.game;

import com.squareup.moshi.Json;
import tech.zolhungaj.amqapi.servercommands.Command;
import tech.zolhungaj.amqapi.servercommands.CommandType;
import tech.zolhungaj.amqapi.servercommands.objects.QuizIdentifier;

import java.util.List;
import java.util.Map;

public record GameStarting(
        @Json(name = "showSelection")
        int showSelection,
        @Json(name = "players")
        List<QuizPlayer> players,
        @Json(name = "groupSlotMap")
        Map<String, List<Integer>> groupSlotMap,
        @Json(name = "multipleChoice")
        boolean multipleChoiceEnabled,
        @Json(name = "quizDescription")
        QuizIdentifier quizIdentifier,
        @Json(name = "gameMode")
        String gameMode
) implements Command {

    @Override
    public String commandName() {
        return CommandType.GAME_STARTING.commandName;
    }
}
