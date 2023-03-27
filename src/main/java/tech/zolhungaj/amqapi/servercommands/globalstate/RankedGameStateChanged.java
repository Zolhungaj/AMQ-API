package tech.zolhungaj.amqapi.servercommands.globalstate;

import com.squareup.moshi.Json;
import tech.zolhungaj.amqapi.constants.AmqRanked;
import tech.zolhungaj.amqapi.servercommands.Command;
import tech.zolhungaj.amqapi.servercommands.CommandType;

public record RankedGameStateChanged(
        @Json(name = "serieId") int seriesId,
        @Json(name = "state") int stateId
)implements Command {

    public AmqRanked.GAME_SERIES series(){
        return AmqRanked.GAME_SERIES.forId(seriesId);
    }

    public AmqRanked.RANKED_STATE state(){
        return AmqRanked.RANKED_STATE.forId(stateId);
    }

    @Override
    public String commandName() {
        return CommandType.RANKED_STATE_CHANGE.commandName;
    }
}
