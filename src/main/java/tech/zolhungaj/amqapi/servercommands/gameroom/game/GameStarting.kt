package tech.zolhungaj.amqapi.servercommands.gameroom.game

import com.squareup.moshi.Json
import tech.zolhungaj.amqapi.servercommands.CommandType
import tech.zolhungaj.amqapi.servercommands.objects.QuizIdentifier
import tech.zolhungaj.amqapi.servercommands.objects.QuizPlayer

@JvmRecord
@CommandType("Game Starting")
data class GameStarting(
    @Json(name = "showSelection")
    val showSelection: Int,
    @Json(name = "players")
    val players: List<QuizPlayer>,
    @Json(name = "groupSlotMap")
    val groupSlotMap: Map<String, List<Int>>,
    @Json(name = "multipleChoice")
    val multipleChoiceEnabled: Boolean,
    @Json(name = "quizDescription")
    val quizIdentifier: QuizIdentifier,
    @Json(name = "gameMode")
    val gameMode: String
) 