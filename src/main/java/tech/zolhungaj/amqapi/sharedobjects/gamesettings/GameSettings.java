package tech.zolhungaj.amqapi.sharedobjects.gamesettings;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.squareup.moshi.Json;
import lombok.Builder;
import lombok.Singular;
import lombok.With;

/**
 * A representation of Game Settings, reused across the board
 * @param password The password to be used if isPrivateRoom is set
 * @param isPrivateRoom Whether a room is password protected
 * */
@Builder(toBuilder = true)
@With
public record GameSettings(
	@JsonProperty("endingCategories")
	@Json(name = "endingCategories")
	Categories endingCategories,

	@JsonProperty("guessTime")
	@Json(name = "guessTime")
	GuessTime guessTime,

	@JsonProperty("scoreType")
	@Json(name = "scoreType")
	int scoreType,

	@JsonProperty("animeScore")
	@Json(name = "animeScore")
	AnimeScore animeScore,

	@JsonProperty("samplePoint")
	@Json(name = "samplePoint")
	SamplePoint samplePoint,

	@JsonProperty("modifiers")
	@Json(name = "modifiers")
	Modifiers modifiers,

	@JsonProperty("type")
	@Json(name = "type")
	ShowFormat showFormat,

	@JsonProperty("openingCategories")
	@Json(name = "openingCategories")
	Categories openingCategories,

	@JsonProperty("password")
	@Json(name = "password")
	String password,

	@JsonProperty("privateRoom")
	@Json(name = "privateRoom")
	boolean isPrivateRoom,

	@JsonProperty("watchedDistribution")
	@Json(name = "watchedDistribution")
	int watchedDistribution,

	@JsonProperty("songPopularity")
	@Json(name = "songPopularity")
	SongPopularity songPopularity,

	@JsonProperty("roomSize")
	@Json(name = "roomSize")
	int roomSize,

	@JsonProperty("genre")
	@Json(name = "genre")
	@Singular("genres")
	List<GenreTag> genres,

	@JsonProperty("lootingTime")
	@Json(name = "lootingTime")
	LootingTime lootingTime,

	@JsonProperty("extraGuessTime")
	@Json(name = "extraGuessTime")
	ExtraGuessTime extraGuessTime,

	@JsonProperty("playbackSpeed")
	@Json(name = "playbackSpeed")
	PlaybackSpeed playbackSpeed,

	@JsonProperty("lives")
	@Json(name = "lives")
	int lives,

	@JsonProperty("songSelection")
	@Json(name = "songSelection")
	SongSelection songSelection,

	@JsonProperty("songType")
	@Json(name = "songType")
    SongTypeSelection songTypeSelection,

	@JsonProperty("roomName")
	@Json(name = "roomName")
	String roomName,

	@JsonProperty("inventorySize")
	@Json(name = "inventorySize")
	InventorySize inventorySize,

	@JsonProperty("tags")
	@Json(name = "tags")
	@Singular("tags")
	List<GenreTag> tags,

	@JsonProperty("insertCategories")
	@Json(name = "insertCategories")
	Categories insertCategories,

	@JsonProperty("showSelection")
	@Json(name = "showSelection")
	int showSelection,

	@JsonProperty("teamSize")
	@Json(name = "teamSize")
	int teamSize,

	@JsonProperty("playerScore")
	@Json(name = "playerScore")
	PlayerScore playerScore,

	@JsonProperty("vintage")
	@Json(name = "vintage")
	Vintage vintage,
	@JsonProperty("numberOfSongs")
	@Json(name = "numberOfSongs")
	int numberOfSongs,

	@JsonProperty("songDifficulity")
	@Json(name = "songDifficulity")
	SongDifficulty songDifficulty,
	@JsonProperty("gameMode")
	@Json(name = "gameMode")
	String gameMode,

	@JsonProperty("answeringMode")
	@Json(name = "answeringMode")
	int answeringMode,
	@JsonProperty("bossLives")
	@Json(name = "bossLives")
	int bossLives,
	@JsonProperty("bossPowerUps")
	@Json(name = "bossPowerUps")
	int bossPowerUps,
	@JsonProperty("bossMaxSongs")
	@Json(name = "bossMaxSongs")
	int bossMaxSongs,
	@JsonProperty("songPool")
	@Json(name = "songPool")
	int songPool
) {
	public enum ScoreType{
		COUNT(1),
		SPEED(2),
		LIVES(3),
		BOSS(4);
		public final int value;
		ScoreType(int value){
			this.value = value;
		}
		static final ScoreType DEFAULT = COUNT;
	}

	public enum WatchedDistribution{
		RANDOM(1),
		WEIGHTED(2),
		EQUAL(3);
		public final int value;
		WatchedDistribution(int value){
			this.value = value;
		}
		static final WatchedDistribution DEFAULT = RANDOM;
	}
	public enum ShowSelection{
		AUTO(1),
		LOOT(2);
		public final int value;
		ShowSelection(int value){
			this.value = value;
		}
		static final ShowSelection DEFAULT = AUTO;
	}
	public enum GameMode{
		MULTIPLAYER("Multiplayer"),
		SOLO("Solo");
		public final String value;
		GameMode(String value){
			this.value = value;
		}
	}

	public enum AnsweringMode{
		TYPING(1),
		MIXED(2),
		MULTIPLE_CHOICE(3);
		public final int value;
		AnsweringMode(int value){
			this.value = value;
		}
		static final AnsweringMode DEFAULT = TYPING;
	}

	public enum SongPool{
		ALL_SONGS(1),
		SINGLE_PLAYER_LIST(2),
		CUSTOM_LIST(3),
		COMMUNITY_QUIZ(4);
		public final int value;
		SongPool(int value){
			this.value = value;
		}
		static final SongPool DEFAULT = ALL_SONGS;
	}

	public static int DEFAULT_NUMBER_OF_SONGS = 20;
	public static int DEFAULT_ROOM_SIZE = 8;
	public static int DEFAULT_TEAM_SIZE = 1;
	public static int DEFAULT_LIVES = 5;
	public static int DEFAULT_BOSS_LIVES = 3;
	public static int DEFAULT_BOSS_POWER_UPS = 3;
	public static int DEFAULT_BOSS_MAX_SONGS = 10;

	public static GameSettings DEFAULT = builder()
			.openingCategories(Categories.DEFAULT)
			.endingCategories(Categories.DEFAULT)
			.insertCategories(Categories.DEFAULT)
			.guessTime(GuessTime.DEFAULT)
			.scoreType(ScoreType.DEFAULT.value)
			.animeScore(AnimeScore.DEFAULT)
			.playerScore(PlayerScore.DEFAULT)
			.samplePoint(SamplePoint.DEFAULT)
			.modifiers(Modifiers.DEFAULT)
			.showFormat(ShowFormat.DEFAULT)
			.password("")
			.watchedDistribution(WatchedDistribution.DEFAULT.value)
			.songPopularity(SongPopularity.DEFAULT)
			.roomSize(DEFAULT_ROOM_SIZE)
			.lootingTime(LootingTime.DEFAULT)
			.extraGuessTime(ExtraGuessTime.DEFAULT)
			.playbackSpeed(PlaybackSpeed.DEFAULT)
			.lives(DEFAULT_LIVES)
			.songSelection(SongSelection.DEFAULT)
			.numberOfSongs(DEFAULT_NUMBER_OF_SONGS)
			.songPool(SongPool.DEFAULT.value)
			.songTypeSelection(SongTypeSelection.DEFAULT)
			.roomName("DEFAULT")
			.inventorySize(InventorySize.DEFAULT)
			.showSelection(ShowSelection.DEFAULT.value)
			.teamSize(DEFAULT_TEAM_SIZE)
			.vintage(Vintage.DEFAULT)
			.isPrivateRoom(false)
			.songDifficulty(SongDifficulty.DEFAULT)
			.gameMode(GameMode.MULTIPLAYER.value)
			.answeringMode(AnsweringMode.DEFAULT.value)
			.bossLives(DEFAULT_BOSS_LIVES)
			.bossPowerUps(DEFAULT_BOSS_POWER_UPS)
			.bossMaxSongs(DEFAULT_BOSS_MAX_SONGS)
			.build();
}