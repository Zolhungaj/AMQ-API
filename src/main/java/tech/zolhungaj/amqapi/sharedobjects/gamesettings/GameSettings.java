package tech.zolhungaj.amqapi.sharedobjects.gamesettings;

import java.util.List;
import com.squareup.moshi.Json;
import lombok.Builder;
import lombok.With;

@Builder
@With
public record GameSettings(

	@Json(name = "endingCategories")
	Categories endingCategories,

	@Json(name = "guessTime")
	GuessTime guessTime,

	@Json(name = "scoreType")
	int scoreType,

	@Json(name = "animeScore")
	Score animeScore,

	@Json(name = "samplePoint")
	SamplePoint samplePoint,

	@Json(name = "modifiers")
	Modifiers modifiers,

	@Json(name = "type")
	ShowFormat showFormat,

	@Json(name = "openingCategories")
	Categories openingCategories,

	@Json(name = "password")
	String password,

	@Json(name = "watchedDistribution")
	int watchedDistribution,

	@Json(name = "songPopularity")
	SongPopularity songPopularity,

	@Json(name = "roomSize")
	int roomSize,

	@Json(name = "genre")
	List<GenreTag> genre,

	@Json(name = "lootingTime")
	LootingTime lootingTime,

	@Json(name = "extraGuessTime")
	ExtraGuessTime extraGuessTime,

	@Json(name = "playbackSpeed")
	PlaybackSpeed playbackSpeed,

	@Json(name = "lives")
	int lives,

	@Json(name = "songSelection")
	SongSelection songSelection,

	@Json(name = "songType")
    SongTypeSelection songTypeSelection,

	@Json(name = "roomName")
	String roomName,

	@Json(name = "inventorySize")
	InventorySize inventorySize,

	@Json(name = "tags")
	List<GenreTag> tags,

	@Json(name = "insertCategories")
	Categories insertCategories,

	@Json(name = "showSelection")
	int showSelection,

	@Json(name = "teamSize")
	int teamSize,

	@Json(name = "playerScore")
	Score playerScore,

	@Json(name = "vintage")
	Vintage vintage,

	@Json(name = "privateRoom")
	boolean privateRoom,

	@Json(name = "numberOfSongs")
	int numberOfSongs,

	@Json(name = "songDifficulity")
	SongDifficulty songDifficulty
) {
	public enum ScoreType{
		COUNT(0),
		SPEED(1),
		LIVES(2);
		final int value;
		ScoreType(int value){
			this.value = value;
		}
		static final ScoreType DEFAULT = COUNT;
	}

	public enum WatchedDistribution{
		RANDOM(0),
		WEIGHTED(1),
		EQUAL(2);
		final int value;
		WatchedDistribution(int value){
			this.value = value;
		}
		static final WatchedDistribution DEFAULT = RANDOM;
	}
	public enum ShowSelection{
		AUTO(0),
		LOOT(1);
		final int value;
		ShowSelection(int value){
			this.value = value;
		}
		static final ShowSelection DEFAULT = AUTO;
	}
	public static int DEFAULT_NUMBER_OF_SONGS = 20;
	public static int DEFAULT_ROOM_SIZE = 8;
	public static int DEFAULT_TEAM_SIZE = 1;
	public static int DEFAULT_LIVES = 3;

	public static GameSettings DEFAULT = builder()
			.openingCategories(Categories.DEFAULT)
			.endingCategories(Categories.DEFAULT)
			.insertCategories(Categories.DEFAULT)
			.guessTime(GuessTime.DEFAULT)
			.scoreType(ScoreType.DEFAULT.value)
			.animeScore(Score.DEFAULT)
			.playerScore(Score.DEFAULT)
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
			.build();
}