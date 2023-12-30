package tech.zolhungaj.amqapi.servercommands.globalstate;

import com.squareup.moshi.Json;
import tech.zolhungaj.amqapi.servercommands.CommandType;
import tech.zolhungaj.amqapi.servercommands.objects.RankedChampion;

import java.util.List;

@CommandType("ranked champions updated")
public record RankedChampionsUpdate(
	List<RankedChampion> champions,
	@Json(name = "serieId")
	int seriesId
){}