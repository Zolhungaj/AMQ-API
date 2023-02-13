package tech.zolhungaj.amqapi.sharedobjects.gamesettings;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.With;

@Builder
@With
public record GameSettings(

	@JsonProperty("endingCategories")
	Categories endingCategories,

	@JsonProperty("guessTime")
	GuessTime guessTime,

	@JsonProperty("scoreType")
	int scoreType,

	@JsonProperty("animeScore")
	AnimeScore animeScore,

	@JsonProperty("samplePoint")
	SamplePoint samplePoint,

	@JsonProperty("modifiers")
	Modifiers modifiers,

	@JsonProperty("type")
	ShowFormat showFormat,

	@JsonProperty("openingCategories")
	Categories openingCategories,

	@JsonProperty("password")
	String password,

	@JsonProperty("watchedDistribution")
	int watchedDistribution,

	@JsonProperty("songPopularity")
	SongPopularity songPopularity,

	@JsonProperty("roomSize")
	int roomSize,

	@JsonProperty("genre")
	List<GenreTag> genre,

	@JsonProperty("lootingTime")
	LootingTime lootingTime,

	@JsonProperty("extraGuessTime")
	ExtraGuessTime extraGuessTime,

	@JsonProperty("playbackSpeed")
	PlaybackSpeed playbackSpeed,

	@JsonProperty("lives")
	int lives,

	@JsonProperty("songSelection")
	SongSelection songSelection,

	@JsonProperty("songType")
    SongTypeSelection songTypeSelection,

	@JsonProperty("roomName")
	String roomName,

	@JsonProperty("inventorySize")
	InventorySize inventorySize,

	@JsonProperty("tags")
	List<GenreTag> tags,

	@JsonProperty("insertCategories")
	Categories insertCategories,

	@JsonProperty("showSelection")
	int showSelection,

	@JsonProperty("teamSize")
	int teamSize,

	@JsonProperty("playerScore")
	PlayerScore playerScore,

	@JsonProperty("vintage")
	Vintage vintage,

	@JsonProperty("privateRoom")
	boolean privateRoom,

	@JsonProperty("numberOfSongs")
	int numberOfSongs,

	@JsonProperty("songDifficulity")
	SongDifficulty songDifficulty,
	@JsonProperty("gameMode")
	String gameMode
) {
	public enum ScoreType{
		COUNT(1),
		SPEED(2),
		LIVES(3);
		final int value;
		ScoreType(int value){
			this.value = value;
		}
		static final ScoreType DEFAULT = COUNT;
	}

	public enum WatchedDistribution{
		RANDOM(1),
		WEIGHTED(2),
		EQUAL(3);
		final int value;
		WatchedDistribution(int value){
			this.value = value;
		}
		static final WatchedDistribution DEFAULT = RANDOM;
	}
	public enum ShowSelection{
		AUTO(1),
		LOOT(2);
		final int value;
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
	public static int DEFAULT_NUMBER_OF_SONGS = 20;
	public static int DEFAULT_ROOM_SIZE = 8;
	public static int DEFAULT_TEAM_SIZE = 1;
	public static int DEFAULT_LIVES = 5;

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
			.genre(List.of())
			.tags(List.of())
			.lootingTime(LootingTime.DEFAULT)
			.extraGuessTime(ExtraGuessTime.DEFAULT)
			.playbackSpeed(PlaybackSpeed.DEFAULT)
			.lives(DEFAULT_LIVES)
			.songSelection(SongSelection.DEFAULT)
			.numberOfSongs(DEFAULT_NUMBER_OF_SONGS)
			.songTypeSelection(SongTypeSelection.DEFAULT)
			.roomName("DEFAULT")
			.inventorySize(InventorySize.DEFAULT)
			.showSelection(ShowSelection.DEFAULT.value)
			.teamSize(DEFAULT_TEAM_SIZE)
			.vintage(Vintage.DEFAULT)
			.privateRoom(false)
			.songDifficulty(SongDifficulty.DEFAULT)
			.gameMode(GameMode.MULTIPLAYER.value)
			.build();
}