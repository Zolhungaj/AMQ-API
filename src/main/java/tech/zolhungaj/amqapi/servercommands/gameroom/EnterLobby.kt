package tech.zolhungaj.amqapi.servercommands.gameroom;

import com.squareup.moshi.Json;
import tech.zolhungaj.amqapi.servercommands.gameroom.lobby.NewPlayer;
import tech.zolhungaj.amqapi.sharedobjects.gamesettings.GameSettings;

import java.util.List;
import java.util.Map;
import java.util.Objects;


public class EnterLobby {
	@Json(name = "gameId")
	private final int gameId;
	@Json(name = "settings")
	private final GameSettings settings;
	@Json(name = "hostName")
	private final String hostName;
	@Json(name = "inQueue")
	private final List<String> playersInQueue;
	@Json(name = "players")
	private final List<NewPlayer> players;
	@Json(name = "inLobby")
	private final Boolean inLobby;
	@Json(name = "teamFullMap")
	private final Map<Integer, Boolean> mapOfFullTeams;
	@Json(name = "spectators")
	private final List<SpectatorJoined> spectators;
	@Json(name = "numberOfTeams")
	private final int numberOfTeams;

	public EnterLobby(

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
	) {
		this.gameId = gameId;
		this.settings = settings;
		this.hostName = hostName;
		this.playersInQueue = playersInQueue;
		this.players = players;
		this.inLobby = inLobby;
		this.mapOfFullTeams = mapOfFullTeams;
		this.spectators = spectators;
		this.numberOfTeams = numberOfTeams;
	}

	@Json(name = "gameId")
	public int gameId() {
		return gameId;
	}

	@Json(name = "settings")
	public GameSettings settings() {
		return settings;
	}

	@Json(name = "hostName")
	public String hostName() {
		return hostName;
	}

	@Json(name = "inQueue")
	public List<String> playersInQueue() {
		return playersInQueue;
	}

	@Json(name = "players")
	public List<NewPlayer> players() {
		return players;
	}

	@Json(name = "inLobby")
	public Boolean inLobby() {
		return inLobby;
	}

	@Json(name = "teamFullMap")
	public Map<Integer, Boolean> mapOfFullTeams() {
		return mapOfFullTeams;
	}

	@Json(name = "spectators")
	public List<SpectatorJoined> spectators() {
		return spectators;
	}

	@Json(name = "numberOfTeams")
	public int numberOfTeams() {
		return numberOfTeams;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this) return true;
		if (obj == null || obj.getClass() != this.getClass()) return false;
		var that = (EnterLobby) obj;
		return this.gameId == that.gameId &&
				Objects.equals(this.settings, that.settings) &&
				Objects.equals(this.hostName, that.hostName) &&
				Objects.equals(this.playersInQueue, that.playersInQueue) &&
				Objects.equals(this.players, that.players) &&
				Objects.equals(this.inLobby, that.inLobby) &&
				Objects.equals(this.mapOfFullTeams, that.mapOfFullTeams) &&
				Objects.equals(this.spectators, that.spectators) &&
				this.numberOfTeams == that.numberOfTeams;
	}

	@Override
	public int hashCode() {
		return Objects.hash(gameId, settings, hostName, playersInQueue, players, inLobby, mapOfFullTeams, spectators, numberOfTeams);
	}

	@Override
	public String toString() {
		return "EnterLobby[" +
				"gameId=" + gameId + ", " +
				"settings=" + settings + ", " +
				"hostName=" + hostName + ", " +
				"playersInQueue=" + playersInQueue + ", " +
				"players=" + players + ", " +
				"inLobby=" + inLobby + ", " +
				"mapOfFullTeams=" + mapOfFullTeams + ", " +
				"spectators=" + spectators + ", " +
				"numberOfTeams=" + numberOfTeams + ']';
	}

}