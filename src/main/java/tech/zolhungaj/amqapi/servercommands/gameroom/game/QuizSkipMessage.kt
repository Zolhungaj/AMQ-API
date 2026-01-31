package tech.zolhungaj.amqapi.servercommands.gameroom.game

import com.squareup.moshi.Json
import tech.zolhungaj.amqapi.servercommands.CommandType

@JvmRecord
@CommandType("quiz skip message")
data class QuizSkipMessage(
    @Json(name = "messageKey")
    val localisationKey: String,
    @Json(name = "messageData")
    val count: Count?
){
    @JvmRecord
    data class Count(
        @Json(name = "number")
        val votes: Int,
        @Json(name = "number2")
        val votesToPass: Int
    )
}