package tech.zolhungaj.amqapi.sharedobjects.gamesettings;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.squareup.moshi.Json;

public record SamplePoint(

	@JsonProperty("standardValue")
	@Json(name = "standardValue")
	int standardValue,

	@JsonProperty("randomValue")
	@Json(name = "randomValue")
	List<Integer> randomRange,

	@JsonProperty("randomOn")
	@Json(name = "randomOn")
	boolean randomOn
) {
	public enum StartingPoint{
		START,
		MIDDLE,
		END
	}
	public static int MIN = 0;
	public static int MAX = 100;
	public static int DEFAULT_STANDARD_VALUE = 1;
	public static List<Integer> DEFAULT_RANGE = List.of(MIN,MAX);
	public static SamplePoint DEFAULT = of(MIN, MAX);
	public static SamplePoint of(StartingPoint startingPoint){
		int standardValue = switch (startingPoint){
			case START -> 1;
			case MIDDLE -> 2;
			case END -> 3;
		};
		return new SamplePoint(standardValue, DEFAULT_RANGE, false);
	}
	public static SamplePoint of(Range range){
		return of(range.start(), range.end());
	}
	public static SamplePoint of(int start, int end){
		if(MIN <= start && start <= end && end <= MAX){
			return new SamplePoint(DEFAULT_STANDARD_VALUE, List.of(start, end), true);
		}else{
			throw new IllegalArgumentException("MIN <= start <= end <= MAX failed");
		}
	}
}