package tech.zolhungaj.amqapi.servercommands.objects;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.squareup.moshi.Json;

public record SongInfo(
        @Json(name = "songName")
        String songName,
        @Json(name = "animeGenre")
        List<String> animeGenre,

        @Json(name = "animeNames")
        MainAnimeNames mainAnimeNames,
        @Json(name = "artist")
        String artist,

        @Json(name = "animeDifficulty")
        Optional<Double> animeDifficulty,

        @Json(name = "altAnimeNames")
        List<String> alternativeAnimeNames,

        @Json(name = "animeScore")
        Optional<Double> animeScore,
        @Json(name = "type")
        SongType type,

        @Json(name = "urlMap")
        Map<String, Map<String, String>> urlMap,

        @Json(name = "typeNumber")
        int typeNumber,
        @Json(name = "annId")
        int annId,

        @Json(name = "vintage")
        String vintage,
        @Json(name = "animeTags")
        List<String> animeTags,

        @Json(name = "siteIds")
        AnimeListSiteShowIds siteIds,
        @Json(name = "animeType")
        String animeType,
        @Json(name = "highRisk")
        Boolean highRisk,

        @Json(name = "altAnimeNamesAnswers")
        List<String> alternativeAnimeNamesAnswers)
{}