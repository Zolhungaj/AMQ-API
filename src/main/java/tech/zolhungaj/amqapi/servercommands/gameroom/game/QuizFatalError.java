package tech.zolhungaj.amqapi.servercommands.gameroom.game;

import tech.zolhungaj.amqapi.servercommands.Command;
import tech.zolhungaj.amqapi.servercommands.CommandTypeOld;

public class QuizFatalError implements Command {
    @Override
    public String commandName() {
        return CommandTypeOld.QUIZ_FATAL_ERROR.commandName;
    }
}
