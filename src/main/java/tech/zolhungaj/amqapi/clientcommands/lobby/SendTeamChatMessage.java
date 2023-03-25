package tech.zolhungaj.amqapi.clientcommands.lobby;

import lombok.Builder;

@Builder
public record SendTeamChatMessage(
        String message
) implements SendChatMessage {
    @Override
    public boolean isTeamMessage() {
        return true;
    }
}
