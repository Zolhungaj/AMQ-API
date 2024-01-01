package tech.zolhungaj.amqapi.servercommands.gameroom

import com.squareup.moshi.Json
import tech.zolhungaj.amqapi.servercommands.CommandType
import tech.zolhungaj.amqapi.servercommands.objects.messages.Badge
import tech.zolhungaj.amqapi.servercommands.objects.messages.MessageEmoji

@JvmRecord
@CommandType("Game Chat Message")
data class GameChatMessage(
    val sender: String,
    val message: String,
    @Json(name = "modMessage")
    val isModMessage: Boolean,
    @Json(name = "teamMessage")
    val isTeamMessage: Boolean,
    @Json(name = "nameGlow")
    val hasNameGlow: Boolean,
    @Json(name = "nameColor")
    val nameColor: Boolean,
    val badges: List<Badge>,
    val messageId: Int,
    val emojis: MessageEmoji,
    @Json(name = "atEveryone")
    val isAtEveryone: Boolean
)
