package tech.zolhungaj.amqapi.sharedobjects.gamesettings;

import com.squareup.moshi.Json;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public record Categories(

	@Json(name = "standard")
	boolean standard,

	@Json(name = "character")
	boolean character,

	@Json(name = "instrumental")
	boolean instrumental,

	@Json(name = "chanting")
	boolean chanting
) {
	public static Categories DEFAULT = of(Category.ALL);
	public enum Category{
		STANDARD,
		CHARACTER,
		INSTRUMENTAL,
		CHANTING,
		ALL
	}

	public static Categories of(Category... categories){
		return of(List.of(categories));
	}

	public static Categories of(Collection<Category> categories){
		if(categories.contains(Category.ALL)){
			return new Categories(true, true, true, true);
		}
		return new Categories(
				categories.contains(Category.STANDARD),
				categories.contains(Category.CHARACTER),
				categories.contains(Category.INSTRUMENTAL),
				categories.contains(Category.CHANTING)
		);
	}

	public Categories with(Category... categories){
		return with(List.of(categories));
	}

	public Categories with(Collection<Category> categories){
		Set<Category> set = new HashSet<>(from());
		set.addAll(categories);
		return of(set);
	}

	public Categories without(Category... categories){
		return without(List.of(categories));
	}

	public Categories without(Collection<Category> categories){
		Set<Category> set = new HashSet<>(from());
		set.removeAll(categories);
		return of(set);
	}

	public Set<Category> from(){
		Set<Category> set = new HashSet<>();
		if(standard){
			set.add(Category.STANDARD);
		}
		if(character){
			set.add(Category.CHARACTER);
		}
		if(instrumental){
			set.add(Category.INSTRUMENTAL);
		}
		if(chanting){
			set.add(Category.CHANTING);
		}
		return set;
	}
}