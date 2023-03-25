package tech.zolhungaj.amqapi.clientcommands.lobby;

import com.fasterxml.jackson.annotation.JsonProperty;

public sealed interface SendChatMessage extends LobbyCommand permits SendPublicChatMessage, SendTeamChatMessage {
    @JsonProperty("msg")
    String message();

    @JsonProperty("teamMessage")
    boolean isTeamMessage();
    @Override
    default String command() {
        return "game chat message";
    }
}
