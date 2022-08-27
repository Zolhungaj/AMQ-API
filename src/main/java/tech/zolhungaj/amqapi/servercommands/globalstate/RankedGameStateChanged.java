package tech.zolhungaj.amqapi.servercommands.globalstate;

import com.squareup.moshi.Json;
import tech.zolhungaj.amqapi.constants.AmqRanked;
import tech.zolhungaj.amqapi.servercommands.Command;
import tech.zolhungaj.amqapi.servercommands.CommandType;

public record RankedGameStateChanged(
        @Json(name = "serieId") Integer seriesId,
        @Json(name = "state") Integer stateId
)implements Command {

    public AmqRanked.GAME_SERIES series(){
        return AmqRanked.GAME_SERIES.forId(seriesId);
    }

    public AmqRanked.RANKED_STATE state(){
        return AmqRanked.RANKED_STATE.forId(stateId);
    }

    @Override
    public String getCommandName() {
        return CommandType.RANKED_STATE_CHANGE.commandName;
    }
}
