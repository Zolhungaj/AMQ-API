package tech.zolhungaj.amqapi.clientcommands.lobby;

import lombok.NonNull;
import tech.zolhungaj.amqapi.clientcommands.CommandName;

@CommandName("kick player")
public record Kick(@NonNull String playerName) implements LobbyCommand {}
