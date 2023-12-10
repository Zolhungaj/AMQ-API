package tech.zolhungaj.amqapi.servercommands.gameroom.game;

import com.squareup.moshi.Json;
import tech.zolhungaj.amqapi.servercommands.CommandType;
import tech.zolhungaj.amqapi.servercommands.objects.ProgressBarState;

import java.util.List;
import java.util.Optional;

@CommandType("play next song")
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
){}
