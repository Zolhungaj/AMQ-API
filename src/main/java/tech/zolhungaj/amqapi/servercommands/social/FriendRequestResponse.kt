package tech.zolhungaj.amqapi.servercommands.social

import com.squareup.moshi.Json
import tech.zolhungaj.amqapi.servercommands.CommandType

@JvmRecord
@CommandType("friend request")
data class FriendRequestResponse(
    @Json(name = "name")
    val playerName: String,
    val result: Result,
    val reason: String?
) {
    enum class Result {
        @Json(name = "succes")//sic
        SUCCESS,

        @Json(name = "error")
        ERROR
    }
}
