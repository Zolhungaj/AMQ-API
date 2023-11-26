package tech.zolhungaj.amqapi.clientcommands.lobby;

import com.fasterxml.jackson.annotation.JsonProperty;
import tech.zolhungaj.amqapi.clientcommands.CommandName;

@CommandName("game chat message")
public record SendPublicChatMessage(
        @JsonProperty("msg")
        String message
) implements LobbyCommand {
    @JsonProperty("teamMessage")
    public boolean isTeamMessage() {
        return false;
    }
}
