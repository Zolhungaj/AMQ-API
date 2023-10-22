package tech.zolhungaj.amqapi.clientcommands.quiz;

import tech.zolhungaj.amqapi.clientcommands.EmptyClientCommand;

public final class PauseQuiz implements QuizCommand, EmptyClientCommand {
    @Override
    public String command() {
        return "quiz pause";
    }
}
