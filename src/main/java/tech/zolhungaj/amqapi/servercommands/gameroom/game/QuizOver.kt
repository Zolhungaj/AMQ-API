package tech.zolhungaj.amqapi.servercommands.gameroom.game

import com.squareup.moshi.Json
import tech.zolhungaj.amqapi.servercommands.CommandType
import tech.zolhungaj.amqapi.servercommands.gameroom.SpectatorJoined
import tech.zolhungaj.amqapi.servercommands.gameroom.lobby.NewPlayer
import tech.zolhungaj.amqapi.sharedobjects.gamesettings.GameSettings

@CommandType("quiz over")
data class QuizOver(
    @Json(name = "gameId")
    val gameId: Int,
    @Json(name = "settings")
    val settings: GameSettings,
    @Json(name = "hostName")
    val hostName: String,
    @Json(name = "inQueue")
    val playersInQueue: List<String>,
    @Json(name = "players")
    val players: List<NewPlayer>,
    @Json(name = "inLobby")
    val inLobby: Boolean,
    @Json(name = "teamFullMap")
    val mapOfFullTeams: Map<Int, Boolean>,
    @Json(name = "spectators")
    val spectators: List<SpectatorJoined>,
    @Json(name = "numberOfTeams")
    val numberOfTeams: Int
)