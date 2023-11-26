package tech.zolhungaj.amqapi.clientcommands.social;

import com.fasterxml.jackson.annotation.JsonProperty;
import tech.zolhungaj.amqapi.clientcommands.CommandName;

@CommandName("player profile toggle hide")
public record ToggleProfileFieldVisibility(
        @JsonProperty("fieldName")
        String field
) implements SocialCommand{
    public enum FieldName {
        ACCOUNT_CREATION_DATE("creationDate"),
        SONGS_PLAYED_COUNT("songCount"),
        CORRECT_GUESS_PERCENTAGE("guessPercent"),
        CONNECTED_ANIME_LIST("list");
        public final String value;
        FieldName(String value){
            this.value = value;
        }
    }
    public static ToggleProfileFieldVisibility of(FieldName field){
        return new ToggleProfileFieldVisibility(field.value);
    }
}