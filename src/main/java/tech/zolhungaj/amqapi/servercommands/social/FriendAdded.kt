package tech.zolhungaj.amqapi.servercommands.social

import com.squareup.moshi.Json
import tech.zolhungaj.amqapi.servercommands.CommandType
import tech.zolhungaj.amqapi.servercommands.objects.PlayerGameState
import tech.zolhungaj.amqapi.servercommands.objects.PlayerStatus

@JvmRecord
@CommandType("new friend")
data class FriendAdded(
    @Json(name = "name") val playerName: String,
    val online: Boolean,
    val status: PlayerStatus,
    val gameState: PlayerGameState?
)
