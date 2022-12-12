package tech.zolhungaj.amqapi.servercommands.expandlibrary;

import com.squareup.moshi.Json;
import tech.zolhungaj.amqapi.servercommands.Command;
import tech.zolhungaj.amqapi.servercommands.CommandType;

import java.util.Optional;

public record ExpandLibraryEntryUpdated(
	int annId,
	int annSongId,
	String host,
	int resolution,
	@Json(name = "animeStillAvaliable") boolean animeStillAvailable,
	@Json(name = "songStillAvaliable") Optional<Boolean> songStillAvailable
) implements Command {
	@Override
	public String getCommandName() {
		return CommandType.EXPAND_LIBRARY_UPDATE.commandName;
	}
}
