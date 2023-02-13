package tech.zolhungaj.amqapi.sharedobjects.gamesettings;

import com.squareup.moshi.Json;
import lombok.NonNull;

import java.util.Collection;
import java.util.List;

public record SongTypeSelection(

	@Json(name = "standardValue")
	SongTypeToggle toggles,

	@Json(name = "advancedValue")
	SongTypeSelectionCount songTypeSelectionCount
) {
	public enum SongType{
		OPENINGS,
		ENDINGS,
		INSERTS,
		ALL
	}
	public record SongTypeToggle(
			@Json(name = "openings")
			boolean openingsEnabled,

			@Json(name = "endings")
			boolean endingsEnabled,

			@Json(name = "inserts")
			boolean insertsEnabled
	){}

	public record SongTypeSelectionCount(
			@Json(name = "openings")
			int openings,

			@Json(name = "endings")
			int endings,

			@Json(name = "inserts")
			int inserts,

			@Json(name = "random")
			int random
	) {}

	public static SongTypeSelection DEFAULT = of(GameSettings.DEFAULT_NUMBER_OF_SONGS, SongType.OPENINGS, SongType.ENDINGS);

	public static SongTypeSelection of(int songCount, SongType... songTypes){
		return of(songCount, List.of(songTypes));
	}
	public static SongTypeSelection of(int songCount, @NonNull Collection<SongType> songTypes){
		if(songCount <= 0){
			throw new IllegalArgumentException("Invalid amount of songs");
		}
		if(songTypes.isEmpty()){
			throw new IllegalArgumentException("Must choose at least one type");
		}
		final SongTypeToggle toggle;
		if(songTypes.contains(SongType.ALL)){
			toggle = new SongTypeToggle(true, true, true);
		}else{
			toggle = new SongTypeToggle(
					songTypes.contains(SongType.OPENINGS),
					songTypes.contains(SongType.ENDINGS),
					songTypes.contains(SongType.INSERTS)
			);
		}
		return new SongTypeSelection(toggle, new SongTypeSelectionCount(0,0,0,songCount));
	}
	public SongTypeSelection with(int openingCount, int endingCount, int insertCount, int randomCount){
		if(
				(openingCount > 0 && !toggles.openingsEnabled()) ||
				(endingCount > 0 && !toggles.endingsEnabled()) ||
				(insertCount > 0 && !toggles.insertsEnabled())
		){
			throw new IllegalArgumentException("Toggle must be enabled to set count");
		}
		if(openingCount + endingCount + insertCount + randomCount !=
				songTypeSelectionCount().openings + songTypeSelectionCount().endings + songTypeSelectionCount.inserts + songTypeSelectionCount.random){
			throw new IllegalArgumentException("Final count must match input count");
		}
		return new SongTypeSelection(toggles, new SongTypeSelectionCount(openingCount, endingCount, insertCount, randomCount));
	}
}