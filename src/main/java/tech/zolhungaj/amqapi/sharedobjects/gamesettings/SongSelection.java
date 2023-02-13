package tech.zolhungaj.amqapi.sharedobjects.gamesettings;

import com.squareup.moshi.Json;
import lombok.NonNull;

/** Song selection is selected by the value parameter, the selection identifier is derived from the value
 * */
public record SongSelection(

	@Json(name = "standardValue")
	int derivedSelectionIdentifier,

	@Json(name = "advancedValue")
	SelectionCount value
) {
	public record SelectionCount(
			@Json(name = "watched")
			int watched,

			@Json(name = "unwatched")
			int unwatched,

			@Json(name = "random")
			int random
	){
	}

	public enum SelectionIdentifier{
		WATCHED(1),
		MIX(2),
		RANDOM(3)
		;
		final int value;
		SelectionIdentifier(int value){
			this.value = value;
		}
	}

	public static SongSelection DEFAULT = of(SongSelection.SelectionIdentifier.WATCHED, GameSettings.DEFAULT_NUMBER_OF_SONGS);

	public static SongSelection of(@NonNull SelectionCount count){
		return of(count.watched(), count.unwatched(), count.random());
	}

	public static SongSelection of(int watched, int unwatched, int random, int songCount){
		if(watched + unwatched + random == songCount){
			return of(watched, unwatched, random);
		}else{
			throw new IllegalArgumentException("SongCount not equal to sum of values");
		}
	}

	public static SongSelection of(@NonNull SelectionIdentifier identifier, int songCount){
		return switch(identifier){
			case WATCHED -> of(songCount, 0, 0);
			case MIX -> {
				int watched = (int) Math.ceil(songCount*0.8);
				int unwatched = songCount - watched;
				yield of(watched,unwatched,0);
			}
			case RANDOM -> of(0, 0, songCount);
		};
	}

	public static SongSelection of(int watched, int unwatched, int random){
		if(watched < 0 || unwatched < 0 || random < 0 || watched + unwatched + random == 0){
			throw new IllegalArgumentException("Sum must be above zero, and no part can be negative");
		}
		final int derivedSelectionIdentifier;
		if(random == 0 && unwatched == 0){
			derivedSelectionIdentifier = SelectionIdentifier.WATCHED.value;
		}else if(unwatched > 0 || watched > 0){
			//unwatched > 0 always gives MIX, if unwatched==0 random is larger than zero per previous if
			derivedSelectionIdentifier = SelectionIdentifier.MIX.value;
		}else{
			derivedSelectionIdentifier = SelectionIdentifier.RANDOM.value;
		}
		return new SongSelection(derivedSelectionIdentifier, new SelectionCount(watched, unwatched, random));
	}

	public SelectionCount from(){
		return value;
	}
}