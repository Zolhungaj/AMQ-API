package tech.zolhungaj.amqapi.clientcommands.lobby;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
public record SendMessage(
        @JsonProperty("msg")
        String message,
        @JsonProperty("teamMessage")
        Boolean isTeamMessage
) implements LobbyCommand {
    public SendMessage{
        if(isTeamMessage == null){
            isTeamMessage = false;
        }
    }
    public SendMessage(String message){
        this(message, null);
    }
    @Override
    public String command() {
        return "game chat message";
    }
}
