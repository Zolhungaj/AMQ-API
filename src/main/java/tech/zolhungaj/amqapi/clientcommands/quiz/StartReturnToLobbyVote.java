package tech.zolhungaj.amqapi.clientcommands.quiz;

import tech.zolhungaj.amqapi.clientcommands.EmptyClientCommand;

public final class StartReturnToLobbyVote implements QuizCommand, EmptyClientCommand {
    @Override
    public String command() {
        return "start return lobby vote";
    }
}
