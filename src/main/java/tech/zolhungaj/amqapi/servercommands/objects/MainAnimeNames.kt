package tech.zolhungaj.amqapi.servercommands.objects;

import com.squareup.moshi.Json;

import java.util.Optional;

public record MainAnimeNames(
        @Json(name = "romaji")
        Optional<String> romaji,
        @Json(name = "english")
        Optional<String> english) {

}