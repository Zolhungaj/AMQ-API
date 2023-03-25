package tech.zolhungaj.amqapi.clientcommands.lobby;

import tech.zolhungaj.amqapi.clientcommands.EmptyClientCommand;

public final class JoinQueue implements LobbyCommand, EmptyClientCommand {
    @Override
    public String command() {
        return "join game queue";
    }
}
