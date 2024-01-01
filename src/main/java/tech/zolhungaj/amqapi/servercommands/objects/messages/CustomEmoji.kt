package tech.zolhungaj.amqapi.servercommands.objects.messages

import com.squareup.moshi.Json

@JvmRecord
data class CustomEmoji(
    @Json(name = "id")
    val emojiId: Int,
    @Json(name = "name")
    val emojiName: String
)
