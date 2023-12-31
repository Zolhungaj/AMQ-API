package tech.zolhungaj.amqapi.servercommands.globalstate;

import com.squareup.moshi.Json;
import tech.zolhungaj.amqapi.constants.AmqRanked;
import tech.zolhungaj.amqapi.servercommands.CommandType;
import tech.zolhungaj.amqapi.servercommands.objects.RankedLeaderboardEntry;

import java.util.List;

@CommandType("ranked standing updated")
public record RankedLeaderboardUpdate(
        @Json(name = "serieId")
        AmqRanked.RankedSeries rankedSeries,
        @Json(name = "standings")
        List<RankedLeaderboardEntry> leaderboard
){}
