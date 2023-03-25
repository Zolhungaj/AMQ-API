package tech.zolhungaj.amqapi.clientcommands.lobby;

import tech.zolhungaj.amqapi.clientcommands.EmptyClientCommand;

public final class LeaveRoom implements LobbyCommand, EmptyClientCommand {
    @Override
    public String command() {
        return "leave game";
    }
}
