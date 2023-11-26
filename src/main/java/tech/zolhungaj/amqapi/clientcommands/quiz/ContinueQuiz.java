package tech.zolhungaj.amqapi.clientcommands.quiz;

import tech.zolhungaj.amqapi.clientcommands.CommandName;
import tech.zolhungaj.amqapi.clientcommands.EmptyClientCommand;

@EmptyClientCommand
@CommandName("quiz unpause")
public final class ContinueQuiz implements QuizCommand {
}
