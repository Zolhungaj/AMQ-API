package tech.zolhungaj.amqapi.servercommands.gameroom.game

import com.squareup.moshi.Json
import tech.zolhungaj.amqapi.servercommands.CommandType

@JvmRecord
@CommandType("player answered")
data class PlayersAnswered(
    @Json(name = "array")
    val timePerPlayerList: List<TimePerPlayer>
){
    data class TimePerPlayer(val answerTime: Double, val gamePlayerIds: List<Int>)
}
