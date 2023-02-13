package tech.zolhungaj.amqapi.sharedobjects.gamesettings;

import java.util.Collection;
import java.util.List;
import com.squareup.moshi.Json;
import lombok.NonNull;

public record SongPopularity(

	@Json(name = "standardValue")
	PopularityToggle standardToggles,

	@Json(name = "advancedValue")
	List<Integer> advancedRange,

	@Json(name = "advancedOn")
	boolean advancedOn
) {
	public record PopularityToggle(
			@Json(name = "liked")
			boolean liked,

			@Json(name = "mixed")
			boolean mixed,

			@Json(name = "disliked")
			boolean disliked
	){}

	public enum Popularity{
		LIKED,
		MIXED,
		DISLIKED,
		ALL
	}

	public static int MIN = 0;
	public static int MAX = 100;
	public static List<Integer> DEFAULT_RANGE = List.of(MIN,MAX);
	public static PopularityToggle DEFAULT_TOGGLE = new PopularityToggle(true, true, true);
	public static SongPopularity DEFAULT = of(Popularity.ALL);
	public static SongPopularity of(Popularity... popularities){
		return of(List.of(popularities));
	}

	private static SongPopularity of(@NonNull Collection<Popularity> popularities) {
		if(popularities.isEmpty()){
			throw new IllegalArgumentException("At least one popularity must be selected");
		}
		final PopularityToggle toggle;
		if(popularities.contains(Popularity.ALL)){
			toggle = new PopularityToggle(true, true, true);
		}else{
			toggle = new PopularityToggle(
					popularities.contains(Popularity.LIKED),
					popularities.contains(Popularity.MIXED),
					popularities.contains(Popularity.DISLIKED)
			);
		}
		return new SongPopularity(toggle, DEFAULT_RANGE, false);
	}

	public static SongPopularity of(Range range){
		return of(range.start(), range.end());
	}

	public static SongPopularity of(int start, int end){
		if(MIN <= start && start <= end && end <= MAX){
			return new SongPopularity(DEFAULT_TOGGLE, List.of(start, end), true);
		}else{
			throw new IllegalArgumentException("MIN <= start <= end <= MAX failed");
		}
	}
}