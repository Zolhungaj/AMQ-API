package tech.zolhungaj.amqapi.clientcommands.lobby;

import lombok.NonNull;

public record Kick(@NonNull String playerName) implements LobbyCommand {
    @Override
    public String command() {
        return "kick player";
    }
}
