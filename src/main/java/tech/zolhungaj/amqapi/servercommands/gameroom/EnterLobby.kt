package tech.zolhungaj.amqapi.servercommands.gameroom

import tech.zolhungaj.amqapi.servercommands.gameroom.lobby.NewPlayer
import tech.zolhungaj.amqapi.sharedobjects.gamesettings.GameSettings

interface EnterLobby {
    val gameId: Int
    val settings: GameSettings
    val hostName: String
    val playersInQueue: List<String>
    val players: List<NewPlayer>
    val inLobby: Boolean
    val mapOfFullTeams: Map<Int, Boolean>
    val spectators: List<SpectatorJoined>
    val numberOfTeams: Int
}