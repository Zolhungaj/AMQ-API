package tech.zolhungaj.amqapi.clientcommands.social;

import tech.zolhungaj.amqapi.sharedobjects.AnimeList;

public record SetProfileDisplayedAnimeList(
        int listId
) implements SocialCommand{
    public static SetProfileDisplayedAnimeList of(AnimeList list){
        return new SetProfileDisplayedAnimeList(list.id);
    }
    @Override
    public String command() {
        return "player profile set list";
    }
}
