package tech.zolhungaj.amqapi.servercommands.gameroom.game;

import tech.zolhungaj.amqapi.servercommands.Command;
import tech.zolhungaj.amqapi.servercommands.CommandType;

public class GuessPhaseOver implements Command {
    public String commandName() {
        return CommandType.GUESS_PHASE_OVER.commandName;
    }
}
