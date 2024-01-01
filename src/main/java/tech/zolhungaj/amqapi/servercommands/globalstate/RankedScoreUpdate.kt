package tech.zolhungaj.amqapi.servercommands.globalstate;

import com.squareup.moshi.Json;
import tech.zolhungaj.amqapi.servercommands.CommandType;

import java.util.Optional;

@CommandType("ranked score update")
public record RankedScoreUpdate(
        int position,
        Optional<RankedState> oldState,
        RankedState newState,
        @Json(name = "oldBadge")
        String oldBadgeFilename,
        @Json(name = "newBadge")
        String newBadgeFilename
){
    public record RankedState(
            int rank,
            int score
    ){}
}
