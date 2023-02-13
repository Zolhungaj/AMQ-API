package tech.zolhungaj.amqapi.sharedobjects.gamesettings;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import com.squareup.moshi.Json;

public record Score(

	@Json(name = "standardValue")
	List<Integer> range,

	@Json(name = "advancedValue")
	List<Boolean> selectedScores,

	@Json(name = "advancedOn")
	boolean advancedOn
) {
	public enum ScoreValue {
		SCORE_1,
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
	public static List<Integer> DEFAULT_RANGE = List.of(1, 10);
	public static List<Boolean> DEFAULT_SELECTED_SCORES = Collections.nCopies(10, true);
	public static Score DEFAULT = ofRange(1,10);
	public static Score ofRange(int start, int end){
		return new Score(List.of(start, end), DEFAULT_SELECTED_SCORES, false);
	}
	public static Score of(ScoreValue... scores){
		return of(List.of(scores));
	}
	public static Score of(Collection<ScoreValue> scores){
		if(scores.isEmpty()){
			return new Score(DEFAULT_RANGE, DEFAULT_SELECTED_SCORES, false);
		}
		List<Boolean> selectedScores = List.of(
				scores.contains(ScoreValue.SCORE_1),
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
			return new Score(List.of(range.start(), range.end()), DEFAULT_SELECTED_SCORES, false);
		}else{
			return new Score(DEFAULT_RANGE, selectedScores, true);
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