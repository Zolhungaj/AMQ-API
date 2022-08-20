package tech.zolhungaj.amqapi.servercommands;

import com.squareup.moshi.Json;
import tech.zolhungaj.amqapi.servercommands.objects.messages.DirectMessageEmojis;

/**
 * Event that happens as an acknowledgement of a recently sent direct message
 * @param emojis the resolved emojis from the message
 * @param target recipient of message
 * @param message plaintext message
 */
public record DirectMessageResponse(
        DirectMessageEmojis emojis,
        @Json(name = "msg") String message,
        String target
) implements Command{


    @Override
    public String getCommandName() {
        return CommandType.DIRECT_MESSAGE_RESPONSE.commandName;
    }
}
