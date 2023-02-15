package tech.zolhungaj.amqapi.sharedobjects.gamesettings;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.squareup.moshi.Json;

public record AnimeScore(

	@JsonProperty("standardValue")
	@Json(name = "standardValue")
	List<Integer> range,

	@JsonProperty("advancedValue")
	@Json(name = "advancedValue")
	List<Boolean> selectedScores,

	@JsonProperty("advancedOn")
	@Json(name = "advancedOn")
	boolean advancedOn
) {
	public enum ScoreValue {
		SCORE_2,
		SCORE_3,
		SCORE_4,
		SCORE_5,
		SCORE_6,
		SCORE_7,
		SCORE_8,
		SCORE_9,
		SCORE_10
	}
	public static List<Integer> DEFAULT_RANGE = List.of(2, 10);
	public static List<Boolean> DEFAULT_SELECTED_SCORES = Collections.nCopies(9, true);
	public static AnimeScore DEFAULT = ofRange(2,10);
	public static AnimeScore ofRange(int start, int end){
		return new AnimeScore(List.of(start, end), DEFAULT_SELECTED_SCORES, false);
	}
	public static AnimeScore of(ScoreValue... scores){
		return of(List.of(scores));
	}
	public static AnimeScore of(Collection<ScoreValue> scores){
		if(scores.isEmpty()){
			return new AnimeScore(DEFAULT_RANGE, DEFAULT_SELECTED_SCORES, false);
		}
		List<Boolean> selectedScores = List.of(
				scores.contains(ScoreValue.SCORE_2),
				scores.contains(ScoreValue.SCORE_3),
				scores.contains(ScoreValue.SCORE_4),
				scores.contains(ScoreValue.SCORE_5),
				scores.contains(ScoreValue.SCORE_6),
				scores.contains(ScoreValue.SCORE_7),
				scores.contains(ScoreValue.SCORE_8),
				scores.contains(ScoreValue.SCORE_9),
				scores.contains(ScoreValue.SCORE_10)
		);
		Range range = findRange(selectedScores);
		if(range != null){
			return new AnimeScore(List.of(range.start(), range.end()), DEFAULT_SELECTED_SCORES, false);
		}else{
			return new AnimeScore(DEFAULT_RANGE, selectedScores, true);
		}
	}

	private static Range findRange(List<Boolean> input){
		int firstTrue = input.indexOf(Boolean.TRUE);
		int lastTrue = input.lastIndexOf(Boolean.TRUE);
		if(firstTrue == -1){
			return null;
		}
		for(int i = firstTrue; i < lastTrue; i++){
			if(Boolean.FALSE.equals(input.get(i))){
				return null;
			}
		}
		return new Range(firstTrue, lastTrue);
	}
}