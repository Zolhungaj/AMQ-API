package tech.zolhungaj.amqapi.clientcommands.lobby;

import com.fasterxml.jackson.annotation.JsonProperty;

public record PromoteToHost(
        @JsonProperty("playerName")
        String target
) implements LobbyCommand{
    @Override
    public String command() {
        return "promote host";
    }
}
