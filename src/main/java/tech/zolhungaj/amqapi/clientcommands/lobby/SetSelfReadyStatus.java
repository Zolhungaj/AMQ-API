package tech.zolhungaj.amqapi.clientcommands.lobby;

import tech.zolhungaj.amqapi.clientcommands.CommandName;

@CommandName("set ready")
public record SetSelfReadyStatus(boolean ready) implements LobbyCommand{}
