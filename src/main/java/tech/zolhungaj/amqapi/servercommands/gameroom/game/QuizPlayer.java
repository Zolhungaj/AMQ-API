package tech.zolhungaj.amqapi.servercommands.gameroom.game;

import com.squareup.moshi.Json;
import tech.zolhungaj.amqapi.servercommands.objects.PlayerAvatar;

public record QuizPlayer(
		@Json(name = "name")
		String displayName,
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
		@Json(name = "positionSlot")
		int positionSlot,
		@Json(name = "teamPlayer") Object teamPlayer,
		@Json(name = "teamNumber") Object teamNumber,
		@Json(name = "hidden") Object hidden,
		@Json(name = "slot") Object groupSlot,
		Object maxHp,
		Object hp,
		Object shield,
		Object abilityInfo,
		Object buffs,
		Object genreInfo,
		@Json(name = "playerName")
		Object playerNameForNexus, //???????
		@Json(name = "pose")
		int pose,
		@Json(name = "score") int score,
		@Json(name = "teamCaptain")
		Object teamCaptain,
		@Json(name = "multiChoiceActive")
		boolean isMultiChoiceActive,
		@Json(name = "position") Integer position
) {

}