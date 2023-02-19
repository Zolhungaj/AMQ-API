package tech.zolhungaj.amqapi.sharedobjects.gamesettings;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.squareup.moshi.Json;

public record GenreTag(
        @JsonProperty("id")
	    @Json(name = "id")
        int id,
        @JsonProperty("state")
	    @Json(name = "state")
        int stateIdentifier
) {
    public enum State{
        INCLUDED(1),
        EXCLUDED(2),
        OPTIONAL(3);
        public final int value;
        State(int value){
            this.value = value;
        }
    }
    public static GenreTag of(int id, State state){
        return new GenreTag(id, state.value);
    }
}
