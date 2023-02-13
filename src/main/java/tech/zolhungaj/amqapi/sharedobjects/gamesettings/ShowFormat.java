package tech.zolhungaj.amqapi.sharedobjects.gamesettings;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public record ShowFormat(
	@JsonProperty("tv")
	boolean tv,

	@JsonProperty("special")
	boolean special,

	@JsonProperty("ona")
	boolean ona,

	@JsonProperty("ova")
	boolean ova,

	@JsonProperty("movie")
	boolean movie

) {
	public enum Format{
		TV,
		SPECIAL,
		ONA,
		OVA,
		MOVIE,
		ALL //special value for enabling all
	}

	public static ShowFormat DEFAULT = ShowFormat.of(Format.ALL);

	public static ShowFormat of(Format... formats){
		return of(List.of(formats));
	}
	public static ShowFormat of(Collection<Format> formats){
		if(formats.isEmpty()){
			throw new IllegalArgumentException("At least one format must be set");
		}
		if(formats.contains(Format.ALL)){
			return new ShowFormat(true, true, true, true, true);
		}
		return new ShowFormat(
				formats.contains(Format.TV),
				formats.contains(Format.SPECIAL),
				formats.contains(Format.ONA),
				formats.contains(Format.OVA),
				formats.contains(Format.MOVIE)
		);
	}
	public Set<Format> from(){
		Set<Format> set = new HashSet<>();
		if(tv){
			set.add(Format.TV);
		}
		if(special){
			set.add(Format.SPECIAL);
		}
		if(ona){
			set.add(Format.ONA);
		}
		if(ova){
			set.add(Format.OVA);
		}
		if(movie){
			set.add(Format.MOVIE);
		}
		return set;
	}
	public ShowFormat with(Format... formats){
		return with(List.of(formats));
	}

	public ShowFormat with(Collection<Format> formats){
		Set<Format> set = new HashSet<>();
		set.addAll(from());
		set.addAll(formats);
		return of(set);
	}

	public ShowFormat without(Format... formats){
		return without(List.of(formats));
	}

	public ShowFormat without(Collection<Format> formats){
		Set<Format> set = new HashSet<>(from());
		set.removeAll(formats);
		return of(set);
	}
}