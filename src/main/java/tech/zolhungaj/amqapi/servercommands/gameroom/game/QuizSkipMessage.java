package tech.zolhungaj.amqapi.servercommands.gameroom.game;

import com.squareup.moshi.Json;
import tech.zolhungaj.amqapi.servercommands.Command;
import tech.zolhungaj.amqapi.servercommands.CommandTypeOld;

public record QuizSkipMessage(
        @Json(name = "string") //this is from the hack in Client.pollCommand
        String message
) implements Command {
    @Override
    public String commandName() {
        return CommandTypeOld.QUIZ_SKIP_MESSAGE.commandName;
    }
}
