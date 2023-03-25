package tech.zolhungaj.amqapi.clientcommands.lobby;

import tech.zolhungaj.amqapi.clientcommands.EmptyClientCommand;

public final class LeaveQueue implements LobbyCommand, EmptyClientCommand {
    @Override
    public String command() {
        return "leave game queue";
    }
}
