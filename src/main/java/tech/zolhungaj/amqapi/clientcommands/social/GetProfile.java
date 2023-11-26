package tech.zolhungaj.amqapi.clientcommands.social;

import com.fasterxml.jackson.annotation.JsonProperty;
import tech.zolhungaj.amqapi.clientcommands.CommandName;

@CommandName("player profile")
public record GetProfile(
        @JsonProperty("name")
        String target
) implements SocialCommand{}
