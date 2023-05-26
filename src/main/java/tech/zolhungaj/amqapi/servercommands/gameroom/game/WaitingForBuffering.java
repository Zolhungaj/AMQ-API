package tech.zolhungaj.amqapi.servercommands.gameroom.game;

import com.squareup.moshi.Json;
import tech.zolhungaj.amqapi.servercommands.Command;
import tech.zolhungaj.amqapi.servercommands.CommandType;

public record WaitingForBuffering(
        @Json(name = "firstSong")
        boolean isFirstSong
) implements Command {
        @Override
        public String commandName() {
            return CommandType.QUIZ_WAITING_BUFFERING.commandName;
        }
}
