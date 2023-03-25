package tech.zolhungaj.amqapi.clientcommands.lobby;

import tech.zolhungaj.amqapi.clientcommands.EmptyClientCommand;

public final class MarkSelfAsAfkAndSurrenderHostStatusToSomeoneElse implements LobbyCommand, EmptyClientCommand {
    @Override
    public String command() {
        return "host afk";
    }
}
