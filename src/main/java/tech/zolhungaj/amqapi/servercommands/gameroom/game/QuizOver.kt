package tech.zolhungaj.amqapi.servercommands.gameroom.game

import com.squareup.moshi.Json
import tech.zolhungaj.amqapi.servercommands.CommandType
import tech.zolhungaj.amqapi.servercommands.gameroom.EnterLobby
import tech.zolhungaj.amqapi.servercommands.gameroom.SpectatorJoined
import tech.zolhungaj.amqapi.servercommands.gameroom.lobby.NewPlayer
import tech.zolhungaj.amqapi.sharedobjects.gamesettings.GameSettings

@CommandType("quiz over")
@JvmRecord
data class QuizOver(
    @Json(name = "gameId")
    override val gameId: Int,
    @Json(name = "settings")
    override val settings: GameSettings,
    @Json(name = "hostName")
    override val hostName: String,
    @Json(name = "inQueue")
    override val playersInQueue: List<String>,
    @Json(name = "players")
    override val players: List<NewPlayer>,
    @Json(name = "inLobby")
    override val inLobby: Boolean,
    @Json(name = "teamFullMap")
    override val mapOfFullTeams: Map<Int, Boolean>,
    @Json(name = "spectators")
    override val spectators: List<SpectatorJoined>,
    @Json(name = "numberOfTeams")
    override val numberOfTeams: Int
) : EnterLobby