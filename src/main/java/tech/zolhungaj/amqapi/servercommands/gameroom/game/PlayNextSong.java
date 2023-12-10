package tech.zolhungaj.amqapi.servercommands.gameroom.game;

import com.squareup.moshi.Json;
import tech.zolhungaj.amqapi.servercommands.Command;
import tech.zolhungaj.amqapi.servercommands.CommandTypeOld;
import tech.zolhungaj.amqapi.servercommands.objects.ProgressBarState;

import java.util.List;
import java.util.Optional;

public record PlayNextSong(
        @Json(name = "songNumber")
        int songNumber,
        @Json(name = "onLastSong")
        boolean onLastSong,
        @Json(name = "multipleChoiceNames")
        Optional<List<String>> multipleChoiceNames,
        @Json(name = "time")
        double time,
        @Json(name = "extraGuessTime")
        int extraGuessTime,
        @Json(name = "progressBarState")
        ProgressBarState progressBarState
) implements Command {

    @Override
    public String commandName() {
        return CommandTypeOld.PLAY_NEXT_SONG.commandName;
    }
}
