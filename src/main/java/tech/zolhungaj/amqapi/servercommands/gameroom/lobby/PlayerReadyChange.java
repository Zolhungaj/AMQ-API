package tech.zolhungaj.amqapi.servercommands.gameroom.lobby;

import tech.zolhungaj.amqapi.servercommands.Command;
import tech.zolhungaj.amqapi.servercommands.CommandType;

public record PlayerReadyChange(
        boolean ready,
        int gamePlayerId
) implements Command {
    @Override
    public String commandName() {
        return CommandType.PLAYER_READY_CHANGE.commandName;
    }
}
