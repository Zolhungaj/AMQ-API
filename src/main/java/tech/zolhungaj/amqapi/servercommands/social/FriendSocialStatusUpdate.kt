package tech.zolhungaj.amqapi.servercommands.social

import com.squareup.moshi.Json
import tech.zolhungaj.amqapi.servercommands.CommandType
import tech.zolhungaj.amqapi.servercommands.objects.PlayerGameState
import tech.zolhungaj.amqapi.servercommands.objects.PlayerStatus

@JvmRecord
@CommandType("friend social status change")
data class FriendSocialStatusUpdate(
    @Json(name = "name")
    val playerName: String,
    @Json(name = "socialStatus")
    val status: PlayerStatus = PlayerStatus.OFFLINE,
    val gameState: PlayerGameState?
)
