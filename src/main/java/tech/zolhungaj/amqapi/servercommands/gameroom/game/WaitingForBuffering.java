package tech.zolhungaj.amqapi.servercommands.gameroom.game;

import com.squareup.moshi.Json;
import tech.zolhungaj.amqapi.servercommands.Command;
import tech.zolhungaj.amqapi.servercommands.CommandTypeOld;

public record WaitingForBuffering(
        @Json(name = "firstSong")
        boolean isFirstSong
) implements Command {
        @Override
        public String commandName() {
            return CommandTypeOld.QUIZ_WAITING_BUFFERING.commandName;
        }
}
