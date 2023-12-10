package tech.zolhungaj.amqapi.servercommands.gameroom.game;

import tech.zolhungaj.amqapi.servercommands.Command;
import tech.zolhungaj.amqapi.servercommands.CommandTypeOld;

public class QuizNoSongs implements Command {
    @Override
    public String commandName() {
        return CommandTypeOld.QUIZ_NO_SONGS.commandName;
    }
}
