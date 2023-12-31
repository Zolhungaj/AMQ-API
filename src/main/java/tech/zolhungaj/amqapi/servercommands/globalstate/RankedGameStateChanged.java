package tech.zolhungaj.amqapi.servercommands.globalstate;

import com.squareup.moshi.Json;
import tech.zolhungaj.amqapi.constants.AmqRanked;
import tech.zolhungaj.amqapi.servercommands.CommandType;

@CommandType("ranked game state change")
public record RankedGameStateChanged(
        @Json(name = "serieId") AmqRanked.RankedSeries rankedSeries,
        @Json(name = "state") AmqRanked.RankedState state
){}
