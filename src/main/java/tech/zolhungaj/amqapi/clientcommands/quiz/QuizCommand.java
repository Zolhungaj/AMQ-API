package tech.zolhungaj.amqapi.clientcommands.quiz;

import tech.zolhungaj.amqapi.clientcommands.ClientCommand;

public sealed interface QuizCommand extends ClientCommand permits StartReturnToLobbyVote {
    @Override
    default String type() {
        return "quiz";
    }
}
