package tech.zolhungaj.amqapi.commands;

import com.squareup.moshi.Json;
import tech.zolhungaj.amqapi.commands.objects.RankedLeaderboardEntry;
import tech.zolhungaj.amqapi.constants.AmqRanked;

import java.util.List;

public record RankedLeaderboardUpdate(
        @Json(name = "serieId") Integer seriesId,
        @Json(name = "standings") List<RankedLeaderboardEntry> leaderboard
)implements Command {

    public AmqRanked.GAME_SERIES series(){
        return AmqRanked.GAME_SERIES.forId(seriesId);
    }

    @Override
    public String getCommandName() {
        return CommandType.RANKED_LEADERBOARD_UPDATE.commandName;
    }
}
