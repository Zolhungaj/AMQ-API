package tech.zolhungaj.amqapi.servercommands.objects;

import lombok.NonNull;

import java.util.Optional;

public record VideoWithVolumeAndResolution(
        @NonNull String video,
        Optional<Integer> volume,
        int resolution
) {
}
