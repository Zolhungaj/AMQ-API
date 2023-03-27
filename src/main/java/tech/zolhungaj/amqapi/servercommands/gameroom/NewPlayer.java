package tech.zolhungaj.amqapi.servercommands.gameroom;

import com.squareup.moshi.Json;
import tech.zolhungaj.amqapi.servercommands.Command;
import tech.zolhungaj.amqapi.servercommands.CommandType;
import tech.zolhungaj.amqapi.servercommands.objects.PlayerAvatar;

import java.util.Optional;

public record NewPlayer(

	@Json(name = "level")
	int level,

	@Json(name = "ready")
	Boolean ready,

	@Json(name = "name")
	String playerName,

	@Json(name = "teamNumber")
	Optional<Integer> teamNumber,

	@Json(name = "gamePlayerId")
	int gamePlayerId,

	@Json(name = "avatar")
	PlayerAvatar avatar,

	@Json(name = "inGame")
	Boolean inGame
) implements Command {
	@Override
	public String commandName() {
		return CommandType.NEW_PLAYER.commandName;
	}
}