package tech.zolhungaj.amqapi.servercommands.social;

import com.squareup.moshi.Json;
import tech.zolhungaj.amqapi.servercommands.CommandType;
import tech.zolhungaj.amqapi.servercommands.objects.messages.DirectMessageEmojis;

/**
 * Event that happens as an acknowledgement of a recently sent direct message
 * @param emojis the resolved emojis from the message
 * @param target recipient of message
 * @param message plaintext message
 */
@CommandType("chat message response")
public record DirectMessageResponse(
        DirectMessageEmojis emojis,
        @Json(name = "msg") String message,
        String target
){}
