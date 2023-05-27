package tech.zolhungaj.amqapi.servercommands.gameroom.lobby;

import java.util.List;
import java.util.Map;

import tech.zolhungaj.amqapi.servercommands.Command;
import tech.zolhungaj.amqapi.servercommands.CommandType;
import tech.zolhungaj.amqapi.servercommands.gameroom.EnterLobby;
import tech.zolhungaj.amqapi.servercommands.gameroom.SpectatorJoined;
import tech.zolhungaj.amqapi.sharedobjects.gamesettings.GameSettings;


public class GameHosted extends EnterLobby implements Command {
	public GameHosted(int gameId, GameSettings settings, String hostName, List<String> playersInQueue, List<NewPlayer> players, Boolean inLobby, Map<Integer, Boolean> mapOfFullTeams, List<SpectatorJoined> spectators, int numberOfTeams) {
		super(gameId, settings, hostName, playersInQueue, players, inLobby, mapOfFullTeams, spectators, numberOfTeams);
	}

	@Override
	public String commandName() {
		return CommandType.HOST_GAME.commandName;
	}
}