package tech.zolhungaj.amqapi.servercommands.expandlibrary;

import com.squareup.moshi.Json;
import tech.zolhungaj.amqapi.servercommands.Command;
import tech.zolhungaj.amqapi.servercommands.CommandType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public record ExpandLibraryEntryList (
        Boolean success,
        String issue,
        @Json(name = "questions") List<ExpandLibraryEntry> entries
) implements Command {
    @Override
    public String getCommandName() {
        return CommandType.EXPAND_LIBRARY_ENTRIES.commandName;
    }

    public record ExpandLibraryEntry (
            @Json(name = "name") String animeName,
            @Json(name = "annId") Integer animeNewsNetworkId,
            List<ExpandLibrarySong> songs
    ) {}
    public record ExpandLibrarySong (
        @Json(name = "annSongId") Integer songId,
        @Json(name = "type") Integer typeId,
        Integer number,
        String artist,
        @Json(name = "name") String songName,
        @Json(name = "examples") Map<String, String> resolutionUrlMap,
        @Json(name = "versions") ExpandLibrarySongVersionEntry versionStatus
    ) {
        public String typeName(){
            return switch(typeId){
                case 1 -> "OP" + number;
                case 2 -> "ED" + number;
                case 3 -> "IN";
                default -> "UNKNOWN TYPE %d, NUMBER %d".formatted(typeId, number);
            };
        }

    }
    public record ExpandLibrarySongVersionEntry (
            @Json(name = "closed") Map<String, ExpandLibrarySongVersionEntryClosed> closedMap,
            @Json(name = "open") Map<String, Map<String, Integer>> openStatusMapOriginal
    ) {
        public Map<String, Map<String, EXPAND_SONG_STATUS>> openStatusMap(){
            final Map<String, Map<String, EXPAND_SONG_STATUS>> versions = new HashMap<>();
            openStatusMapOriginal.forEach(
                    (host, statusMap) -> {
                        final Map<String, EXPAND_SONG_STATUS> statuses = new HashMap<>();
                        versions.put(host, statuses);
                        statusMap.forEach(
                                (resolution, statusId) -> statuses.put(resolution, EXPAND_SONG_STATUS.forId(statusId))
                        );
                    }
            );
            return versions;
        }
    }

    public record ExpandLibrarySongVersionEntryClosed (
            Optional<Integer> resolution,
            Integer status
    ) {}
    public enum EXPAND_SONG_STATUS{
        APPROVED(1),
        PENDING(2),
        MISSING(3),
        UNKNOWN(0);

        private static final Map<Integer, EXPAND_SONG_STATUS> ID_MAP = new HashMap<>();
        static{
            for(EXPAND_SONG_STATUS gs : EXPAND_SONG_STATUS.values()){
                ID_MAP.put(gs.id, gs);
            }
        }
        public final Integer id;
        EXPAND_SONG_STATUS(Integer id){
            this.id = id;
        }


        /**
         * @return for valid values return corresponding EXPAND_SONG_STATUS, otherwise return {@link #UNKNOWN}
         */
        public static EXPAND_SONG_STATUS forId(Integer id){
            return ID_MAP.getOrDefault(id, UNKNOWN);
        }
    }
}
