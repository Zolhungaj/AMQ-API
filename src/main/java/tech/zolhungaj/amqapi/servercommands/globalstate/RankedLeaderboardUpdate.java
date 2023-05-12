package tech.zolhungaj.amqapi.servercommands.globalstate;

import com.squareup.moshi.Json;
import tech.zolhungaj.amqapi.servercommands.Command;
import tech.zolhungaj.amqapi.servercommands.CommandType;
import tech.zolhungaj.amqapi.servercommands.objects.RankedLeaderboardEntry;
import tech.zolhungaj.amqapi.constants.AmqRanked;

import java.util.List;

public record RankedLeaderboardUpdate(
        @Json(name = "serieId")AmqRanked.RankedSeries rankedSeries,
        @Json(name = "standings") List<RankedLeaderboardEntry> leaderboard
)implements Command {

    @Override
    public String commandName() {
        return CommandType.RANKED_LEADERBOARD_UPDATE.commandName;
    }
}
