package tech.zolhungaj.amqapi.servercommands.objects

import com.squareup.moshi.Json

@JvmRecord
data class PlayerIdentifier(
    @Json(name = "name")
    val playerName: String,
    val gamePlayerId: Int?
)
