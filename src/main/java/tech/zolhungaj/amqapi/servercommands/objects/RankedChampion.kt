package tech.zolhungaj.amqapi.servercommands.objects;

import com.squareup.moshi.Json;
import org.jetbrains.annotations.NotNull;

public record RankedChampion (
		@Json(name = "name") String championName,
		int position
) implements Comparable<RankedChampion>
{
	@Override
	public int compareTo(@NotNull RankedChampion o) {
		return this.position() - o.position();
	}
}