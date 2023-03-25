package tech.zolhungaj.amqapi.clientcommands.lobby;

import tech.zolhungaj.amqapi.clientcommands.EmptyClientCommand;

public final class StartGame implements LobbyCommand, EmptyClientCommand {
    @Override
    public String command() {
        return "start game";
    }
}