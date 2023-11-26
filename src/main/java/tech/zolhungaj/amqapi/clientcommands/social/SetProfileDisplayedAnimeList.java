package tech.zolhungaj.amqapi.clientcommands.social;

import tech.zolhungaj.amqapi.clientcommands.CommandName;
import tech.zolhungaj.amqapi.sharedobjects.AnimeList;

@CommandName("player profile set list")
public record SetProfileDisplayedAnimeList(int listId) implements SocialCommand{
    public static SetProfileDisplayedAnimeList of(AnimeList list){
        return new SetProfileDisplayedAnimeList(list.id);
    }
}
