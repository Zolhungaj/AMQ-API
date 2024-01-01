package tech.zolhungaj.amqapi.servercommands.objects;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public record VideoMapWithVolume(
        Optional<VideoWithVolumeAndResolution> video1080p,
        Optional<VideoWithVolumeAndResolution> video720p,
        Optional<VideoWithVolumeAndResolution> video480p,
        Optional<VideoWithVolumeAndResolution> audio
) {
    public static List<Integer> VALID_RESOLUTIONS = List.of(1080, 720, 480, 0);

    public static VideoMapWithVolume of(Map<Integer, VideoWithVolumeAndResolution> map) {
        return new VideoMapWithVolume(
                Optional.ofNullable(map.get(1080)),
                Optional.ofNullable(map.get(720)),
                Optional.ofNullable(map.get(480)),
                Optional.ofNullable(map.get(0))
        );
    }
}
