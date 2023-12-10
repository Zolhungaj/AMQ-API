package tech.zolhungaj.amqapi.servercommands.gameroom.game;

import tech.zolhungaj.amqapi.servercommands.Command;
import tech.zolhungaj.amqapi.servercommands.CommandTypeOld;
import tech.zolhungaj.amqapi.servercommands.objects.PlayerAnswer;
import tech.zolhungaj.amqapi.servercommands.objects.ProgressBarState;

import java.util.List;
import java.util.Optional;


public record AnswerReveal(
    List<PlayerAnswer> answers,
    Optional<ProgressBarState> progressBarState
) implements Command {

    @Override
    public String commandName() {
        return CommandTypeOld.PLAYER_ANSWERS.commandName;
    }
}
