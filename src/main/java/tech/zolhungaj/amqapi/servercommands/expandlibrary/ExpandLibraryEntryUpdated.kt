package tech.zolhungaj.amqapi.servercommands.expandlibrary;

import com.squareup.moshi.Json;
import tech.zolhungaj.amqapi.servercommands.CommandType;

import java.util.Optional;

@CommandType("expandLibrary song answered")
public record ExpandLibraryEntryUpdated(
	int annId,
	int annSongId,
	String host,
	int resolution,
	@Json(name = "animeStillAvaliable") Boolean animeStillAvailable,
	@Json(name = "songStillAvaliable") Optional<Boolean> songStillAvailable
) {}
