package tech.zolhungaj.amqapi.clientcommands.quiz;

public final class StartReturnToLobbyVote implements QuizCommand {
    @Override
    public String command() {
        return "start return lobby vote";
    }
}
