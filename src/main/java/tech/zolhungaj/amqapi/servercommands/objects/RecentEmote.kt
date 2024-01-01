package tech.zolhungaj.amqapi.servercommands.objects

import tech.zolhungaj.amqapi.constants.Emojis
import java.net.URI
import kotlin.jvm.optionals.getOrNull

@JvmRecord
data class RecentEmote(
    val emoteId: Int?,  //see EmoteGroup -> Emote
    val emojiId: Int?,
    val shortCode: String?
) {
    fun emojiURI(): URI? {
        return emojiId?.let { Emojis.getEmojiURI(it) }?.getOrNull()
    }

    fun shortCodeEmoji(): String? {
        return shortCode?.let { Emojis.getEmojiFromShortcode(it) }?.getOrNull()
    }
}
