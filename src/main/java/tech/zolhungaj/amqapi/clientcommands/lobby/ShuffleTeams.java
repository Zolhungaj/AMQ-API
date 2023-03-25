package tech.zolhungaj.amqapi.clientcommands.lobby;

import tech.zolhungaj.amqapi.clientcommands.EmptyClientCommand;

public final class ShuffleTeams implements LobbyCommand, EmptyClientCommand {
    @Override
    public String command() {
        return "shuffle teams";
    }
}
