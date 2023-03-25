package tech.zolhungaj.amqapi.clientcommands.lobby;

import tech.zolhungaj.amqapi.clientcommands.EmptyClientCommand;

public final class MoveSelfToPlayer implements LobbyCommand, EmptyClientCommand {
    @Override
    public String command() {
        return "change to player";
    }
}
