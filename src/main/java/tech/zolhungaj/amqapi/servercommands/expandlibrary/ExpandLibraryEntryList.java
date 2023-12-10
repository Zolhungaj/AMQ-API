package tech.zolhungaj.amqapi.servercommands.expandlibrary;

import com.squareup.moshi.Json;
import tech.zolhungaj.amqapi.servercommands.Command;
import tech.zolhungaj.amqapi.servercommands.CommandTypeOld;
import tech.zolhungaj.amqapi.servercommands.objects.SongType;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public record ExpandLibraryEntryList (
        Boolean success,
        String issue,
        @Json(name = "questions") List<ExpandLibraryEntry> entries
) implements Command {
    @Override
    public String commandName() {
        return CommandTypeOld.EXPAND_LIBRARY_ENTRIES.commandName;
    }

    public record ExpandLibraryEntry (
            @Json(name = "name") String animeName,
            @Json(name = "annId") int animeNewsNetworkId,
            List<ExpandLibrarySong> songs
    ) {}
    public record ExpandLibrarySong (
        @Json(name = "annSongId") int songId,
        @Json(name = "type") SongType songType,
        int number,
        String artist,
        @Json(name = "name") String songName,
        @Json(name = "examples") Map<String, String> resolutionUrlMap,
        @Json(name = "versions") ExpandLibrarySongVersionEntry versionStatus
    ) {
        public String typeName(){
            return switch(songType){
                case OPENING -> "OP" + number;
                case ENDING -> "ED" + number;
                case INSERT -> "IN";
                case UNKNOWN -> "UNKNOWN " + number;
            };
        }

    }
    public record ExpandLibrarySongVersionEntry (
            @Json(name = "closed") Map<String, ExpandLibrarySongVersionEntryClosed> closedMap,
            @Json(name = "open") Map<String, Map<String, ExpandSongStatus>> openStatusMap
    ) {
    }

    public record ExpandLibrarySongVersionEntryClosed (
            Optional<Integer> resolution,
            ExpandSongStatus status
    ) {}
    public enum ExpandSongStatus {
        @Json(name = "1")
        APPROVED,
        @Json(name = "2")
        PENDING,
        @Json(name = "3")
        MISSING,
        @Json(name = "0")
        UNKNOWN
    }
}
