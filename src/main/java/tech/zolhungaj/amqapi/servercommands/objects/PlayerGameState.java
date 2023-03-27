package tech.zolhungaj.amqapi.servercommands.objects;

import com.squareup.moshi.Json;

import java.util.Optional;

public record PlayerGameState (
        Optional<Integer> gameId,
        boolean isSpectator,
        @Json(name = "private") boolean isPrivateRoom,
        @Json(name = "soloGame") Optional<Boolean> isSoloGame,
        @Json(name = "isRanked") Optional<Boolean> isRankedGame,
        boolean inLobby
){
    public PlayerGameState{
        // check if optional because of mapping issues in moshi with null values in inner objects
        if(isSoloGame == null){
            isSoloGame = Optional.empty();
        }
        if(isRankedGame == null){
            isRankedGame = Optional.empty();
        }
    }
}
