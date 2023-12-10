package tech.zolhungaj.amqapi.servercommands.gameroom.game;

import tech.zolhungaj.amqapi.servercommands.Command;
import tech.zolhungaj.amqapi.servercommands.CommandTypeOld;
import tech.zolhungaj.amqapi.servercommands.objects.PlayerEndResult;
import tech.zolhungaj.amqapi.servercommands.objects.ProgressBarState;

import java.util.List;

public record QuizEndResult(
        List<PlayerEndResult> resultStates,
        ProgressBarState progressBarState
) implements Command {
    @Override
    public String commandName() {
        return CommandTypeOld.QUIZ_END_RESULT.commandName;
    }
}
