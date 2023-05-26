package tech.zolhungaj.amqapi.servercommands.objects;

import com.squareup.moshi.Json;

import java.util.Map;

public record VideoInfo(
        @Json(name = "videoMap")
        Map<String, Map<String, String>> videoMap,
        @Json(name = "videoVolumeMap")
        Map<String, Map<String, Integer>> videoVolumeMap,
        @Json(name = "id") int id
) {

}