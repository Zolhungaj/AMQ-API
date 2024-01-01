package tech.zolhungaj.amqapi.servercommands.social

import com.squareup.moshi.Json
import tech.zolhungaj.amqapi.servercommands.CommandType
import tech.zolhungaj.amqapi.servercommands.objects.messages.DirectMessageEmojis

/**
 * Event that happens as an acknowledgement of a recently sent direct message
 * @param emojis the resolved emojis from the message
 * @param target recipient of message
 * @param message plaintext message
 */
@JvmRecord
@CommandType("chat message response")
data class DirectMessageResponse(
    val emojis: DirectMessageEmojis,
    @Json(name = "msg")
    val message: String,
    val target: String
)
