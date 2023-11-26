package tech.zolhungaj.amqapi.clientcommands.expandlibrary;

import lombok.*;
import tech.zolhungaj.amqapi.clientcommands.CommandName;

@Builder
@CommandName("expandLibrary answer")
public record SubmitToExpandLibrary(
        int annId,
        int annSongId,
        @NonNull
        String url,
        int resolution
) implements ExpandLibraryCommand {
}
