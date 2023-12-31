package tech.zolhungaj.amqapi.servercommands.gameroom.game

import com.squareup.moshi.Json
import tech.zolhungaj.amqapi.servercommands.CommandType

@JvmRecord
@CommandType("Rejoining Player")
data class PlayerRejoin(
    val gamePlayerId: Int,
    @Json(name = "name")
    val playerName: String
)
