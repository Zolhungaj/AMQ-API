package tech.zolhungaj.amqapi.sharedobjects.gamesettings;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.squareup.moshi.Json;
import lombok.NonNull;

public record PlaybackSpeed(

	@Json(name = "standardValue")
	double standardValue,

	@Json(name = "randomValue")
	List<Boolean> enabledSpeeds,

	@Json(name = "randomOn")
	boolean randomOn
) {
	public enum PlaybackSpeedValue{
		SPEED_1,
		SPEED_1_5,
		SPEED_2,
		SPEED_4,
	}
	private static final List<Boolean> NO_VALUES = List.of(false, false, false, false);
	public static final PlaybackSpeed DEFAULT = of(PlaybackSpeedValue.SPEED_1);

	public static PlaybackSpeed of(@NonNull PlaybackSpeedValue... values){
		return of(List.of(values));
	}

	public static PlaybackSpeed of(@NonNull Collection<PlaybackSpeedValue> values){
		if(values.isEmpty()){
			throw new IllegalArgumentException("At least one speed must be specified");
		}
		if(values.size() == 1){
			PlaybackSpeedValue value = values.stream().findFirst().orElseThrow();
			double standardValue = switch(value){
				case SPEED_1 -> 1.0;
				case SPEED_1_5 -> 1.5;
				case SPEED_2 -> 2.0;
				case SPEED_4 -> 4.0;
			};
			return new PlaybackSpeed(standardValue, NO_VALUES, false);
		}
		boolean randomOn = true;
		List<Boolean> enabledSpeeds = List.of(
				values.contains(PlaybackSpeedValue.SPEED_1),
				values.contains(PlaybackSpeedValue.SPEED_1_5),
				values.contains(PlaybackSpeedValue.SPEED_2),
				values.contains(PlaybackSpeedValue.SPEED_4)
		);
		return new PlaybackSpeed(DEFAULT.standardValue(), enabledSpeeds, randomOn);
	}

	public Set<PlaybackSpeedValue> from(){
		if(randomOn){
			Set<PlaybackSpeedValue> set = new HashSet<>();
			List<PlaybackSpeedValue> available = List.of(
					PlaybackSpeedValue.SPEED_1,
					PlaybackSpeedValue.SPEED_1_5,
					PlaybackSpeedValue.SPEED_2,
					PlaybackSpeedValue.SPEED_4);
			for(int i = 0; i < available.size() && i < enabledSpeeds.size(); i++){
				if(Boolean.TRUE.equals(enabledSpeeds.get(i))){
					set.add(available.get(i));
				}
			}
			return Set.copyOf(set);
		}else{
			final PlaybackSpeedValue value;
			if(standardValue == 1.0){
				value = PlaybackSpeedValue.SPEED_1;
			}else if(standardValue == 1.5){
				value = PlaybackSpeedValue.SPEED_1_5;
			}else if(standardValue == 2.0){
				value = PlaybackSpeedValue.SPEED_2;
			}else if(standardValue == 4.0){
				value = PlaybackSpeedValue.SPEED_4;
			}else{
				value = PlaybackSpeedValue.SPEED_1;
			}
			return Set.of(value);
		}
	}
}