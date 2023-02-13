package tech.zolhungaj.amqapi.sharedobjects.gamesettings;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Collection;
import java.util.List;

/**
 * There is a mapping for the different difficulties to ranges,
 * but the toggles allow a split range,
 * and notes/exp gain has a tendency to be hard mapped to difficulties regardless of the actual ranges
 * so this object does not provide any from function*/
public record SongDifficulty(

	@JsonProperty("standardValue")
	DifficultyToggle difficultyToggle,

	@JsonProperty("advancedValue")
	List<Integer> advancedRange,

	@JsonProperty("advancedOn")
	boolean advancedOn
) {
	public enum Difficulty{
		EASY,
		MEDIUM,
		HARD,
		ALL
	}
	public record DifficultyToggle(

			@JsonProperty("easy")
			boolean easy,
			@JsonProperty("medium")
			boolean medium,
			@JsonProperty("hard")
			boolean hard
	){}
	public static int MIN = 0;
	public static int MAX = 100;
	public static List<Integer> DEFAULT_RANGE = List.of(MIN,MAX);
	public static DifficultyToggle DEFAULT_TOGGLE = new DifficultyToggle(true, true, true);
	public static SongDifficulty DEFAULT = new SongDifficulty(DEFAULT_TOGGLE, DEFAULT_RANGE, false);

	public static SongDifficulty of(Difficulty... difficulties){
		return of(List.of(difficulties));
	}

	public static SongDifficulty of(Collection<Difficulty> difficulties){
		final DifficultyToggle toggle;
		if(difficulties.contains(Difficulty.ALL)){
			toggle = new DifficultyToggle(true, true, true);
		}else{
			toggle = new DifficultyToggle(
					difficulties.contains(Difficulty.EASY),
					difficulties.contains(Difficulty.MEDIUM),
					difficulties.contains(Difficulty.HARD)
			);
		}

		return new SongDifficulty(
				toggle,
				DEFAULT_RANGE,
				false
		);
	}

	public static SongDifficulty of(Range range){
		return of(range.start(), range.end());
	}

	public static SongDifficulty of(int start, int end){
		if(MIN <= start && start <= end && end <= MAX){
			return new SongDifficulty(DEFAULT_TOGGLE, List.of(start, end), true);
		}else{
			throw new IllegalArgumentException("MIN <= start <= end <= MAX failed");
		}
	}
}