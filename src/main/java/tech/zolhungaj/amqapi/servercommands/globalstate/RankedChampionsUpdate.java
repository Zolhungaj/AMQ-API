package tech.zolhungaj.amqapi.servercommands.globalstate;

import tech.zolhungaj.amqapi.servercommands.Command;
import tech.zolhungaj.amqapi.servercommands.CommandType;
import tech.zolhungaj.amqapi.servercommands.objects.RankedChampion;

import java.util.List;

public record RankedChampionsUpdate(
	List<RankedChampion> champions,
	int serieId
) implements Command {
	@Override
	public String getCommandName() {
		return CommandType.RANKED_CHAMPIONS_UPDATED.commandName;

	}
}