package tech.zolhungaj.amqapi.servercommands.objects;

import com.squareup.moshi.Json;

import java.util.Optional;

public record AnimeListSiteShowIds(
        @Json(name = "kitsuId")
        Optional<Integer> kitsuId,
        @Json(name = "annId")
        Optional<Integer> animeNewsNetworkId,
        @Json(name = "malId") Optional<Integer> myAnimeListId,
        @Json(name = "aniListId") Optional<Integer> aniListId)
{}