package tech.zolhungaj.amqapi.servercommands.gameroom.game;

import tech.zolhungaj.amqapi.servercommands.Command;
import tech.zolhungaj.amqapi.servercommands.CommandTypeOld;

public class GuessPhaseOver implements Command {
    public String commandName() {
        return CommandTypeOld.GUESS_PHASE_OVER.commandName;
    }
}
