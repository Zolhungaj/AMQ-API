package tech.zolhungaj.amqapi.servercommands.gameroom.game;

import tech.zolhungaj.amqapi.servercommands.Command;
import tech.zolhungaj.amqapi.servercommands.CommandTypeOld;

public record QuizReady(
        int numberOfSongs
) implements Command {

    @Override
    public String commandName() {
        return CommandTypeOld.QUIZ_READY.commandName;
    }
}
