package tech.zolhungaj.amqapi.clientcommands.expandlibrary;

import lombok.*;

@Builder
public record SubmitToExpandLibrary(
        int annId,
        int annSongId,
        @NonNull
        String url,
        int resolution
) implements ExpandLibraryCommand {
    @Override
    public String command() {
        return "expandLibrary answer";
    }
}
