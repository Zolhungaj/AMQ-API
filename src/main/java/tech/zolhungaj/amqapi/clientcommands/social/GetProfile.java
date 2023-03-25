package tech.zolhungaj.amqapi.clientcommands.social;

import com.fasterxml.jackson.annotation.JsonProperty;

public record GetProfile(
        @JsonProperty("name")
        String target
) implements SocialCommand{
    @Override
    public String command() {
        return "player profile";
    }
}
