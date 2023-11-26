package tech.zolhungaj.amqapi.clientcommands.lobby;

import tech.zolhungaj.amqapi.clientcommands.CommandName;

@CommandName("join team")
public record JoinTeam(int teamNumber) implements LobbyCommand{}
