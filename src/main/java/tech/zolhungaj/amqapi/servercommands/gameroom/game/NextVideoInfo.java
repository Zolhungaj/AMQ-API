package tech.zolhungaj.amqapi.servercommands.gameroom.game;

import com.squareup.moshi.Json;
import tech.zolhungaj.amqapi.servercommands.Command;
import tech.zolhungaj.amqapi.servercommands.CommandTypeOld;
import tech.zolhungaj.amqapi.servercommands.objects.VideoInfo;

public record NextVideoInfo(
        @Json(name = "playbackSpeed")
        double playbackSpeed,
        @Json(name = "startPont")
        double startPoint,
        @Json(name = "videoInfo")
        VideoInfo videoInfo,
        @Json(name = "playLength")
        int playLength
) implements Command {

        @Override
        public String commandName() {
            return CommandTypeOld.QUIZ_NEXT_VIDEO_INFO.commandName;
        }
}
