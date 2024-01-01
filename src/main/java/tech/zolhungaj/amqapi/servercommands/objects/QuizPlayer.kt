package tech.zolhungaj.amqapi.servercommands.objects;

import com.squareup.moshi.Json;

import java.util.Optional;

public record QuizPlayer(
		@Json(name = "name")
		String playerName,
		@Json(name = "level")
		int level,
		@Json(name = "gamePlayerId")
		int gamePlayerId,
		@Json(name = "host")
		boolean isHost,
		@Json(name = "avatarInfo")
		PlayerAvatar avatarInfo,
		@Json(name = "inGame")
		boolean inGame,
		@Json(name = "teamPlayer") Optional<Boolean> teamPlayer,
		@Json(name = "teamNumber") Optional<Integer> teamNumber,
		@Json(name = "pose")
		AvatarPose pose,
		@Json(name = "score")
		int score,
		@Json(name = "teamCaptain")
		Optional<Boolean> teamCaptain,
		@Json(name = "multiChoiceActive")
		boolean hasMultiChoiceActive,
		@Json(name = "position") int position,
		@Json(name = "positionSlot") Optional<Integer> positionSlot
) {

}