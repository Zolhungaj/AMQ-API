package tech.zolhungaj.amqapi.servercommands.gameroom.game;

import com.squareup.moshi.Json;
import tech.zolhungaj.amqapi.servercommands.Command;
import tech.zolhungaj.amqapi.servercommands.CommandTypeOld;
import tech.zolhungaj.amqapi.servercommands.objects.PlayerAnswerResult;
import tech.zolhungaj.amqapi.servercommands.objects.ProgressBarState;
import tech.zolhungaj.amqapi.servercommands.objects.SongInfo;

import java.util.List;
import java.util.Map;

public record AnswerResults(
        @Json(name = "watched")
        boolean isWatched,
        @Json(name = "groupMap")
        Map<String, List<Integer>> groupMap,
        @Json(name = "players")
        List<PlayerAnswerResult> players,
        @Json(name = "songInfo")
        SongInfo songInfo,
        @Json(name = "progressBarState")
        ProgressBarState progressBarState
) implements Command {

        @Override
        public String commandName() {
            return CommandTypeOld.ANSWER_RESULTS.commandName;
        }
}
