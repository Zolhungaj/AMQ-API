package tech.zolhungaj.amqapi.commands.objects;

import com.squareup.moshi.Json;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public record PlayerGameState (
        Optional<Integer> gameId,
        Boolean isSpectator,
        @Json(name = "private") Boolean isPrivateRoom,
        @Json(name = "soloGame") @Nullable Boolean isSoloGame,
        @Json(name = "isRanked") @Nullable Boolean isRankedGame,
        Boolean inLobby
){
    public Optional<Boolean> isSoloGameOptional(){
        return Optional.ofNullable(isSoloGame);
    }
    public Optional<Boolean> isRankedGameOptional(){
        return Optional.ofNullable(isRankedGame);
    }
}
