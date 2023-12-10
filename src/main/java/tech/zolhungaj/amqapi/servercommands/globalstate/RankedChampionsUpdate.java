package tech.zolhungaj.amqapi.servercommands.globalstate;

import com.squareup.moshi.Json;
import tech.zolhungaj.amqapi.servercommands.Command;
import tech.zolhungaj.amqapi.servercommands.CommandTypeOld;
import tech.zolhungaj.amqapi.servercommands.objects.RankedChampion;

import java.util.List;

public record RankedChampionsUpdate(
	List<RankedChampion> champions,
	@Json(name = "serieId")
	int seriesId
) implements Command {
	@Override
	public String commandName() {
		return CommandTypeOld.RANKED_CHAMPIONS_UPDATED.commandName;

	}
}