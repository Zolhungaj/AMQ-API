package tech.zolhungaj.amqapi.servercommands.globalstate

import com.squareup.moshi.Json
import tech.zolhungaj.amqapi.servercommands.CommandType
import tech.zolhungaj.amqapi.servercommands.objects.PlayerGameState

@JvmRecord
@CommandType("new friend")
data class FriendAdded(
    @Json(name = "name") val playerName: String,
    val online: Boolean,
    val status: Int,
    val gameState: PlayerGameState
)
