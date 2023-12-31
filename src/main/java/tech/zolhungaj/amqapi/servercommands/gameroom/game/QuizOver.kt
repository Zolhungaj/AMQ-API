package tech.zolhungaj.amqapi.servercommands.gameroom.game;

import tech.zolhungaj.amqapi.servercommands.CommandType;
import tech.zolhungaj.amqapi.servercommands.gameroom.EnterLobby;
import tech.zolhungaj.amqapi.servercommands.gameroom.SpectatorJoined;
import tech.zolhungaj.amqapi.servercommands.gameroom.lobby.NewPlayer;
import tech.zolhungaj.amqapi.sharedobjects.gamesettings.GameSettings;

import java.util.List;
import java.util.Map;

@CommandType("quiz over")
public class QuizOver extends EnterLobby {
	public QuizOver(int gameId, GameSettings settings, String hostName, List<String> playersInQueue, List<NewPlayer> players, Boolean inLobby, Map<Integer, Boolean> mapOfFullTeams, List<SpectatorJoined> spectators, int numberOfTeams) {
		super(gameId, settings, hostName, playersInQueue, players, inLobby, mapOfFullTeams, spectators, numberOfTeams);
	}
}