package tech.zolhungaj.amqapi.servercommands.gameroom.game;

import tech.zolhungaj.amqapi.servercommands.Command;
import tech.zolhungaj.amqapi.servercommands.CommandType;

/*
* Indicates that the server wants the client to send the feedback for the current song
* Only happens at the end of a game, because this is otherwise triggered by PlayNextSong*/
public class SongFeedbackRequest implements Command {
    public String commandName() {
        return CommandType.SEND_FEEDBACK.commandName;
    }
}
