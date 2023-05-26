package tech.zolhungaj.amqapi.servercommands.gameroom.lobby;

import java.util.List;
import java.util.Map;

import com.squareup.moshi.Json;
import tech.zolhungaj.amqapi.servercommands.Command;
import tech.zolhungaj.amqapi.servercommands.CommandType;
import tech.zolhungaj.amqapi.servercommands.gameroom.SpectatorJoined;
import tech.zolhungaj.amqapi.sharedobjects.gamesettings.GameSettings;

public record HostGame(

	@Json(name = "gameId")
	int gameId,

	@Json(name = "settings")
	GameSettings settings,

	@Json(name = "hostName")
	String hostName,

	@Json(name = "inQueue")
	List<String> playersInQueue,

	@Json(name = "players")
	List<NewPlayer> players,

	@Json(name = "inLobby")
	Boolean inLobby,

	@Json(name = "teamFullMap")
	Map<Integer, Boolean> mapOfFullTeams,

	@Json(name = "spectators")
	List<SpectatorJoined> spectators,

	@Json(name = "numberOfTeams")
	int numberOfTeams
) implements Command {
	@Override
	public String commandName() {
		return CommandType.HOST_GAME.commandName;
	}
}