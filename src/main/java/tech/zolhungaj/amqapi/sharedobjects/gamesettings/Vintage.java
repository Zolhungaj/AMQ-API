package tech.zolhungaj.amqapi.sharedobjects.gamesettings;

import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.squareup.moshi.Json;

/**One or more ranges in which songs may appear
 * @param seasonRange the selected range iff seasonRanges is empty
 * @param seasonRanges the selected ranges, overrides seasonRange if set
 * */
public record Vintage(
		@JsonProperty("advancedValueList")
		@Json(name = "advancedValueList")
		List<SeasonRange> seasonRanges,

		@JsonProperty("standardValue")
		@Json(name = "standardValue")
		SeasonRange seasonRange
) {
	public record SeasonRange(

			@JsonProperty("seasons")
			@Json(name = "seasons")
			List<Integer> seasons,

			@JsonProperty("years")
			@Json(name = "years")
			List<Integer> years
	) {
		public static SeasonRange of(int startYear, Season startSeason, int endYear, Season endSeason){
			return new SeasonRange(List.of(startSeason.value, endSeason.value), List.of(startYear, endYear));
		}
	}
	public enum Season{
		WINTER(0),
		SPRING(1),
		SUMMER(2),
		AUTUMN(3), FALL(3) //i18n
		;
		public final int value;
		Season(int value){
			this.value = value;
		}
	}

	public static int MIN_YEAR = 1924;
	public static int MAX_YEAR = LocalDate.now().getYear(); //should work forever
	public static SeasonRange DEFAULT_RANGE = SeasonRange.of(MIN_YEAR, Season.WINTER, MAX_YEAR, Season.AUTUMN);
	public static Vintage DEFAULT = new Vintage(List.of(), DEFAULT_RANGE);

	public static Vintage of(int startYear, Season startSeason, int endYear, Season endSeason){
		return of(SeasonRange.of(startYear, startSeason, endYear, endSeason));
	}

	public static Vintage of(SeasonRange... ranges){
		return of(List.of(ranges));
	}

	public static Vintage of(Collection<SeasonRange> ranges){
		if(ranges.isEmpty()){
			throw new IllegalArgumentException("At least one range must be set");
		}
		if(ranges.size() == 1){
			return new Vintage(List.of(), ranges.stream().findFirst().orElseThrow());
		}
		Set<SeasonRange> set = new HashSet<>(ranges);
		return new Vintage(set.stream().toList(), DEFAULT_RANGE);
	}
}