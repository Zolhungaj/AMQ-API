package tech.zolhungaj.amqapi.servercommands.globalstate;

import com.squareup.moshi.Json;
import tech.zolhungaj.amqapi.servercommands.Command;
import tech.zolhungaj.amqapi.servercommands.CommandType;

import java.util.Optional;

public record RankedScoreUpdate(
        int position,
        Optional<RankedState> oldState,
        RankedState newState,
        @Json(name = "oldBadge")
        String oldBadgeFilename,
        @Json(name = "newBadge")
        String newBadgeFilename
) implements Command {
    @Override
    public String commandName() {
        return CommandType.RANKED_SCORE_UPDATE.commandName;
    }
    public record RankedState(
            int rank,
            int score
    ){}
}
