package tech.zolhungaj.amqapi.clientcommands.quiz;

import tech.zolhungaj.amqapi.clientcommands.EmptyClientCommand;

public final class ContinueQuiz implements QuizCommand, EmptyClientCommand {
    @Override
    public String command() {
        return "quiz unpause";
    }
}
