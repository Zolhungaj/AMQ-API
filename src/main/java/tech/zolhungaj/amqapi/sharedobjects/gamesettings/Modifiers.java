package tech.zolhungaj.amqapi.sharedobjects.gamesettings;

import com.squareup.moshi.Json;
import lombok.NonNull;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public record Modifiers(
		@Json(name = "lootDropping")
		boolean allowLootDropping,

		@Json(name = "skipGuessing")
		boolean allowGuessPhaseSkipping,

		@Json(name = "skipReplay")
		boolean allowReplayPhaseSkipping,

		@Json(name = "queueing")
		boolean allowRoomQueueing,

		@Json(name = "duplicates")
		boolean allowDuplicateShows,

		@Json(name = "rebroadcastSongs")
		boolean allowRebroadcastSongs,

		@Json(name = "dubSongs")
		boolean allowDubSongs


) {
	public enum Modifier {
		ALLOW_LOOT_DROPPING,
		ALLOW_GUESS_PHASE_SKIPPING,
		ALLOW_REPLAY_PHASE_SKIPPING,
		ALLOW_ROOM_QUEUING,
		ALLOW_DUPLICATE_SHOWS,
		ALLOW_REBROADCAST_SONGS,
		ALLOW_DUB_SONGS,
		ALL
	}

	public static Modifiers DEFAULT = of(Modifier.ALL).without(Modifier.ALLOW_DUB_SONGS);

	public static Modifiers of(Modifier... modifiers){
		return of(List.of(modifiers));
	}

	public static Modifiers of(@NonNull Collection<Modifier> modifiers){
		if(modifiers.contains(Modifier.ALL)){
			return new Modifiers(
					true,
					true,
					true,
					true,
					true,
					true,
					true);
		}
		return new Modifiers(
				modifiers.contains(Modifier.ALLOW_LOOT_DROPPING),
				modifiers.contains(Modifier.ALLOW_GUESS_PHASE_SKIPPING),
				modifiers.contains(Modifier.ALLOW_REPLAY_PHASE_SKIPPING),
				modifiers.contains(Modifier.ALLOW_ROOM_QUEUING),
				modifiers.contains(Modifier.ALLOW_DUPLICATE_SHOWS),
				modifiers.contains(Modifier.ALLOW_REBROADCAST_SONGS),
				modifiers.contains(Modifier.ALLOW_DUB_SONGS)
		);
	}
	public Modifiers with(Modifier... categories){
		return with(List.of(categories));
	}

	public Modifiers with(Collection<Modifier> modifiers){
		Set<Modifier> set = new HashSet<>(from());
		set.addAll(modifiers);
		return of(set);
	}

	public Modifiers without(Modifier... modifiers){
		return without(List.of(modifiers));
	}

	public Modifiers without(Collection<Modifier> modifiers){
		Set<Modifier> set = new HashSet<>(from());
		set.removeAll(modifiers);
		return of(set);
	}

	public Set<Modifier> from(){
		Set<Modifier> set = new HashSet<>();
		if(allowLootDropping){
			set.add(Modifier.ALLOW_LOOT_DROPPING);
		}
		if(allowGuessPhaseSkipping){
			set.add(Modifier.ALLOW_GUESS_PHASE_SKIPPING);
		}
		if(allowReplayPhaseSkipping){
			set.add(Modifier.ALLOW_REPLAY_PHASE_SKIPPING);
		}
		if(allowRoomQueueing){
			set.add(Modifier.ALLOW_ROOM_QUEUING);
		}
		if(allowDuplicateShows){
			set.add(Modifier.ALLOW_DUPLICATE_SHOWS);
		}
		if(allowRebroadcastSongs){
			set.add(Modifier.ALLOW_REBROADCAST_SONGS);
		}
		if(allowDubSongs){
			set.add(Modifier.ALLOW_DUB_SONGS);
		}
		return set;
	}
}