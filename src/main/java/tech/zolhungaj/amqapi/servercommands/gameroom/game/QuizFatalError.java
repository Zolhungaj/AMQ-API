package tech.zolhungaj.amqapi.servercommands.gameroom.game;

import tech.zolhungaj.amqapi.servercommands.Command;
import tech.zolhungaj.amqapi.servercommands.CommandType;

public class QuizFatalError implements Command {
    @Override
    public String commandName() {
        return CommandType.QUIZ_FATAL_ERROR.commandName;
    }
}
