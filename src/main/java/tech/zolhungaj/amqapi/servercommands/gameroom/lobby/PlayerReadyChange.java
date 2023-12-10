package tech.zolhungaj.amqapi.servercommands.gameroom.lobby;

import tech.zolhungaj.amqapi.servercommands.Command;
import tech.zolhungaj.amqapi.servercommands.CommandTypeOld;

public record PlayerReadyChange(
        Boolean ready,
        int gamePlayerId
) implements Command {
    @Override
    public String commandName() {
        return CommandTypeOld.PLAYER_READY_CHANGE.commandName;
    }
}
