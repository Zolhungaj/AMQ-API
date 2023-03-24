package tech.zolhungaj.amqapi.clientcommands.lobby;

import tech.zolhungaj.amqapi.clientcommands.ClientCommand;

public sealed interface LobbyCommand extends ClientCommand permits Kick, SendMessage {
    @Override
    default String type() {
        return "lobby";
    }
}
