package tech.zolhungaj.amqapi.clientcommands.lobby;

import com.fasterxml.jackson.annotation.JsonProperty;

public record MovePlayerToSpectator(
        @JsonProperty("playerName")
        String target
) implements LobbyCommand{
    @Override
    public String command() {
        return "change player to spectator";
    }
}
