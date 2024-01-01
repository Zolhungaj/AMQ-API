package tech.zolhungaj.amqapi.servercommands.social

import com.squareup.moshi.Json
import tech.zolhungaj.amqapi.servercommands.CommandType

@JvmRecord
@CommandType("new friend request recived") //sic
data class FriendRequestReceived(
    @Json(name = "name")
    val playerName: String
)
