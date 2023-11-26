package tech.zolhungaj.amqapi.clientcommands.lobby;

import com.fasterxml.jackson.annotation.JsonProperty;
import tech.zolhungaj.amqapi.clientcommands.CommandName;

@CommandName("change player to spectator")
public record MovePlayerToSpectator(
        @JsonProperty("playerName")
        String target
) implements LobbyCommand{}
