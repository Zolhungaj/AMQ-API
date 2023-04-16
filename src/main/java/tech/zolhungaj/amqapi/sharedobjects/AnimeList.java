package tech.zolhungaj.amqapi.sharedobjects;

import java.util.HashMap;
import java.util.Map;

public enum AnimeList {
    ANILIST(1),
    KITSU(2),
    MYANIMELIST(3);

    private static final Map<Integer, AnimeList> ID_MAP = new HashMap<>();
    static{
        for(AnimeList gs : AnimeList.values()){
            ID_MAP.put(gs.id, gs);
        }
    }

    public final int id;
    AnimeList(int id){
        this.id = id;
    }

    public static AnimeList forId(int id){
        return ID_MAP.getOrDefault(id, null);
    }
}
