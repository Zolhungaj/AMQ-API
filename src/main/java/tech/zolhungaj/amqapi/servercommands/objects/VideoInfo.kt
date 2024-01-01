package tech.zolhungaj.amqapi.servercommands.objects;

import com.squareup.moshi.Json;
import lombok.extern.slf4j.Slf4j;

import java.util.*;

@Slf4j
public record VideoInfo(
        @Json(name = "videoMap")
        Map<String, Map<String, String>> videoMap,
        @Json(name = "videoVolumeMap")
        Map<String, Map<String, Integer>> videoVolumeMap,
        @Json(name = "id") int id
) {
    private static final String CATBOX = "catbox";
    private static final String OPENINGS_MOE = "openingsmoe";
    public Optional<VideoMapWithVolume> catbox(){
        Optional<Map<String, String>> videosOptional = Optional.ofNullable(videoMap.get(CATBOX));
        Optional<Map<String, Integer>> volumesOptional = Optional.ofNullable(videoVolumeMap.get(CATBOX));
        if(videosOptional.isEmpty()) return Optional.empty();
        Map<String, String> videos = videosOptional.get();
        Map<Integer, VideoWithVolumeAndResolution> resolutionMap = new HashMap<>();
        for(Map.Entry<String, String> entry : videos.entrySet()){
            int resolution = Integer.parseInt(entry.getKey());
            String video = entry.getValue();
            Optional<Integer> volume = volumesOptional
                    .map(map -> map.get(entry.getKey()));
            VideoWithVolumeAndResolution newEntry = new VideoWithVolumeAndResolution(video, volume, resolution);
            if(VideoMapWithVolume.VALID_RESOLUTIONS.contains(resolution)){
                resolutionMap.put(resolution, newEntry);
            } else {
                log.warn("Unknown resolution {} for catbox video {}", resolution, video);
            }
        }
        return Optional.of(VideoMapWithVolume.of(resolutionMap));
    }

    public Optional<VideoWithVolumeAndResolution> openingsmoe(){
        Optional<Map<String, String>> videosOptional = Optional.ofNullable(videoMap.get(OPENINGS_MOE));
        if(videosOptional.isEmpty()) return Optional.empty();
        Map<String, String> videos = videosOptional.get();
        Set<String> resolutions = videos.keySet();
        if (resolutions.size() > 1){
            log.warn("More than one openings.moe video found, using first one");
        }
        Optional<String> resolutionOptional = resolutions.stream().findFirst();
        if(resolutionOptional.isEmpty()) return Optional.empty();
        String resolution = resolutionOptional.get();
        String video = videos.get(resolution);
        Optional<Integer> volume = Optional.ofNullable(videoVolumeMap.get(OPENINGS_MOE))
                .map(map -> map.get(resolution));
        return Optional.of(new VideoWithVolumeAndResolution(video, volume, Integer.parseInt(resolution)));
    }
}