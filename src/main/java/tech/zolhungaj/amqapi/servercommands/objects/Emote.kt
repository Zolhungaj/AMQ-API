package tech.zolhungaj.amqapi.servercommands.objects

import com.squareup.moshi.Json

@JvmRecord
data class Emote(
    val tierId: Int,
    @Json(name = "name") val emoteName: String,
    val emoteId: Int
)
