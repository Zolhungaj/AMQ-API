package tech.zolhungaj.amqapi.servercommands.gameroom.game;

import tech.zolhungaj.amqapi.servercommands.Command;
import tech.zolhungaj.amqapi.servercommands.CommandTypeOld;
import tech.zolhungaj.amqapi.servercommands.gameroom.EnterLobby;
import tech.zolhungaj.amqapi.servercommands.gameroom.SpectatorJoined;
import tech.zolhungaj.amqapi.servercommands.gameroom.lobby.NewPlayer;
import tech.zolhungaj.amqapi.sharedobjects.gamesettings.GameSettings;

import java.util.List;
import java.util.Map;


public class QuizOver extends EnterLobby implements Command {
	public QuizOver(int gameId, GameSettings settings, String hostName, List<String> playersInQueue, List<NewPlayer> players, Boolean inLobby, Map<Integer, Boolean> mapOfFullTeams, List<SpectatorJoined> spectators, int numberOfTeams) {
		super(gameId, settings, hostName, playersInQueue, players, inLobby, mapOfFullTeams, spectators, numberOfTeams);
	}

	@Override
	public String commandName() {
		return CommandTypeOld.QUIZ_OVER.commandName;
	}
}