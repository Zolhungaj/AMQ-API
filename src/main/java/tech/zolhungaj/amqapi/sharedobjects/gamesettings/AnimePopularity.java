package tech.zolhungaj.amqapi.sharedobjects.gamesettings;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.squareup.moshi.Json;
import java.util.List;

public record AnimePopularity(

	@JsonProperty("standardValue")
	@Json(name = "standardValue")
	int standardValue,

	@JsonProperty("advancedValue")
	@Json(name = "advancedValue")
	List<Integer> advancedRange,

	@JsonProperty("advancedOn")
	@Json(name = "advancedOn")
	boolean advancedOn
) {

	public enum Popularity{
		TOP_100(1),
		TOP_500(2),
		TOP_1000(3),
		TOP_2000(4),
		ALL(5);
		public final int value;
		Popularity(int value){
			this.value = value;
		}
	}

	//inclusive
	public static int MIN = 1;
	public static int MAX = 9999; //lol

	public static List<Integer> DEFAULT_RANGE = List.of(MIN,MAX);
	public static Popularity DEFAULT_POPULARITY = Popularity.ALL;
	public static AnimePopularity DEFAULT = of(DEFAULT_POPULARITY);
	public static AnimePopularity of(Popularity popularity){
		return new AnimePopularity(popularity.value, DEFAULT_RANGE, false);
	}

	public static AnimePopularity of(Range range){
		return of(range.start(), range.end());
	}

	private static AnimePopularity of(int min, int max) {
		if(MIN <= min && min <= max && max <= MAX){
			return new AnimePopularity(DEFAULT_POPULARITY.value, List.of(min, max), true);
		}else{
			throw new IllegalArgumentException("MIN <= min <= max <= MAX failed");
		}
	}
}