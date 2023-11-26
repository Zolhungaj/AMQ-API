package tech.zolhungaj.amqapi.clientcommands.lobby;

import com.fasterxml.jackson.annotation.JsonProperty;
import tech.zolhungaj.amqapi.clientcommands.CommandName;

@CommandName("promote host")
public record PromoteToHost(
        @JsonProperty("playerName")
        String target
) implements LobbyCommand{}
