package tech.zolhungaj.amqapi.clientcommands.social;

public record SetProfileDisplayedAnimeList(
        int listId
) implements SocialCommand{
    public enum AnimeList{
        ANILIST(1),
        KITSU(2),
        MYANIMELIST(3);

        public final int id;
        AnimeList(int id){
            this.id = id;
        }
    }
    public static SetProfileDisplayedAnimeList of(AnimeList list){
        return new SetProfileDisplayedAnimeList(list.id);
    }
    @Override
    public String command() {
        return "player profile set list";
    }
}
