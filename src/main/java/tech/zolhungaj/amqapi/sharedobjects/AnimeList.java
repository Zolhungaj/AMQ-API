package tech.zolhungaj.amqapi.sharedobjects;

import com.squareup.moshi.Json;

public enum AnimeList {
    @Json(name = "1")
    ANILIST(1),
    @Json(name = "2")
    KITSU(2),
    @Json(name = "3")
    MYANIMELIST(3);

    public final int id;
    AnimeList(int id){
        this.id = id;
    }
}
