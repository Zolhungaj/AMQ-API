package tech.zolhungaj.amqapi.servercommands.gameroom.game;

import com.squareup.moshi.Json;
import tech.zolhungaj.amqapi.servercommands.CommandType;
import tech.zolhungaj.amqapi.servercommands.objects.VideoInfo;

@CommandType("quiz next video info")
public record NextVideoInfo(
        @Json(name = "playbackSpeed")
        double playbackSpeed,
        @Json(name = "startPont")
        double startPoint,
        @Json(name = "videoInfo")
        VideoInfo videoInfo,
        @Json(name = "playLength")
        int playLength
){}
