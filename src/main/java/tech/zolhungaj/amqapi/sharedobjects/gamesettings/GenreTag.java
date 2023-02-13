package tech.zolhungaj.amqapi.sharedobjects.gamesettings;

import com.squareup.moshi.Json;

public record GenreTag(
        @Json(name = "id")
        int id,
        @Json(name = "state")
        int stateIdentifier
) {
    public enum State{
        INCLUDED(1),
        EXCLUDED(2),
        OPTIONAL(3);
        final int value;
        State(int value){
            this.value = value;
        }
    }
    public static GenreTag of(int id, State state){
        return new GenreTag(id, state.value);
    }
}
