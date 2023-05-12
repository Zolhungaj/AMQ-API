package tech.zolhungaj.amqapi.servercommands.globalstate;

import com.squareup.moshi.Json;
import tech.zolhungaj.amqapi.constants.AmqRanked;
import tech.zolhungaj.amqapi.servercommands.Command;
import tech.zolhungaj.amqapi.servercommands.CommandType;

public record RankedGameStateChanged(
        @Json(name = "serieId") AmqRanked.RankedSeries rankedSeries,
        @Json(name = "state") AmqRanked.RankedState state
        )implements Command {

    @Override
    public String commandName() {
        return CommandType.RANKED_STATE_CHANGE.commandName;
    }
}
