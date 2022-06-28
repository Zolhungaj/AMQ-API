package tech.zolhungaj.amqapi.commands;

import org.jetbrains.annotations.NotNull;
import org.json.JSONPropertyName;
import tech.zolhungaj.amqapi.Emojis;

import java.net.URI;
import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public record LoginComplete(
    Boolean gameAdmin,
    List<QuestDescription> questDescriptions,
    List<SavedQuizSetting> savedQuizSettings,
    PatreonBadgeInfo patreonBadgeInfo,
    RewardAlert rewardAlert,
    Integer driveTotal,
    List<DriveContribution> top5AllTime,
    Boolean displayArtContestPopUp,
    @JSONPropertyName("top5AvatarNominatios") List<AvatarNomination> top5AvatarNominations,
    Integer patreonId,
    Map<String, Integer> avatarUnlockCount,
    Map<String, Map<String, Boolean>> unlockedDesign,
    List<TicketReward> recentTicketRewards,
    Integer rankedSerie, //TODO: figure out meaning
    List<SuperAvatar> defaultAvatars,
    Integer backerLevel,
    UserSettings settings,
    Integer level,
    List<Integer> unlockedEmoteIds,
    @JSONPropertyName("malName") Optional<String> mal,
    Optional<String> anilist,
    Optional<String> kitsu,
    Optional<String> malLastUpdate,
    Optional<String> aniListLastUpdate,
    Optional<String> kitsuLastUpdate,
    List<FriendEntry> friends,
    RankedChampions rankedChampions,
    Integer nameChangeTokens,
    //List<Void> blockedPlayers, //TODO: get example of content
    List<EmoteGroup> emoteGroups,
    Integer tickets,
    @JSONPropertyName("top5Montly") List<CumulativeAvatarDonation> top5Monthly,
    List<ServerStatus> serverStatuses,
    Boolean topAdmin,
    Boolean useRomajiName,
    Integer questTokenProgress,
    @JSONPropertyName("tagInfo") List<AnimeTag> tags,
    RankedLeaderboard rankedLeaderboards,
    List<RollTarget> rollTargets,
    XPInfo xpInfo,
    Integer credits,
    @JSONPropertyName("genreInfo") List<AnimeGenre> genres,
    Boolean tutorial,
    Boolean canReconnectGame,
    List<AvatarDonation> recentDonations,
    Integer avatarTokens,
    Boolean freeDonation,
    Map<String, Integer> characterUnlockCount,
    List<CustomEmoji> customEmojis,
    PlayerAvatar avatar,
    Boolean PatreonDesynced,
    Integer rhythm,
    List<String> videoHostNames,
    Boolean twitterClaimed,
    List<PlayerAvatar> favoriteAvatars, //TODO: verify
    List<CumulativeAvatarDonation> top5Weekly,
    Integer rankedState,
    Boolean discordClaimed,
    Integer expandCount,
    List<RecentEmote> recentEmotes,
    Boolean saleTax,
    @JSONPropertyName("self") String selfName,
    Integer badgeLevel,
    Boolean guestAccount,
    TutorialState tutorialState
)
implements Command{
    public Optional<Instant> anilistLastUpdateInstant(){
        return aniListLastUpdate.map(Instant::parse);
    }
    public Optional<Instant> kitsuLastUpdateInstant(){
        return kitsuLastUpdate.map(Instant::parse);
    }
    public Optional<Instant> malLastUpdateInstant(){
        return malLastUpdate.map(Instant::parse);
    }

    @Override
    public String getName() {
        return CommandType.GAME_INVITE.commandName;
    }

    public record QuestDescription(
            Integer ticketReward,
            Integer questId,
            Integer targetState,
            Integer weekSlot,
            String name,
            String description,
            Integer state,
            Integer noteReward
    ){}

    public record SavedQuizSetting (
            String name,
            String settingString,
            Integer id
    ){}

    public record PatreonBadgeInfo (
            PatreonBadge next,
            PatreonBadge current
    ){}

    public record PatreonBadge (
            Boolean special,
            String fileName,
            String name,
            Integer id,
            Integer type,
            String unlockDescription
    ){}

    public record DriveContribution(
            Double amount,
            String name
    ){}

    public record AvatarNomination (
            String name,
            Double value
    ){}

    public record TicketReward (
            Integer tier,
            TicketRewardDescription description,
            String type,
            Integer rhythm
    ){}

    public record TicketRewardDescription (
            Integer tierId,
            String name,
            Integer emoteId,
            String colorName,
            Optional<String> editor,
            Integer colorId,
            Boolean active,
            Boolean optionActive,
            String backGroundFileName,
            Boolean colorActive,
            String avatarName,
            Integer avatarId,
            String outfitName,
            Integer sizeModifier,
            String optionName,
            Integer characterId
    ){}

    public record SuperAvatar(
            Integer characterId,
            List<Avatar> avatars
    ){}

    public record Avatar (
            String colorName,
            Integer outfitId,
            Optional<Integer> patreonTierToUnlock,
            Integer notePrice,
            String lore,
            Boolean limited,
            String artist,
            String badgeName,
            String backgroundVert,
            Boolean active,
            List<AvatarColor> colors,
            Integer defaultColorId,
            String badgeFileName,
            String avatarName,
            Boolean defaultAvatar,
            Integer avatarId,
            String world,
            Optional<Integer> tierId,
            Integer realMoneyPrice,
            String outfitName,
            Boolean exclusive,
            Integer sizeModifier,
            String optionName
    ){}

    public record AvatarColor (
            Optional<String> editor,
            Boolean limited,
            Integer colorId,
            Boolean active,
            String backgroundVert,
            Boolean defaultColor,
            Optional<Integer> tierId,
            Integer price,
            Boolean eventColor, //Always 0?
            Boolean unique,
            String name,
            Boolean exclusive,
            Integer sizeModifier
    ){}

    public record UserSettings (
            Boolean autoHideInserts,
            Boolean disableEmojis,
            Integer animeList,
            Boolean voteSkipReplay,
            Boolean showTeamAnswersState,
            Boolean autoHideEndings,
            Boolean useOnHold,
            Boolean useRomajiNames,
            Boolean equalizeSound,
            Boolean shareScore,
            Boolean voteSkipGuess,
            Boolean usePlanning,
            Boolean autoSubmit,
            @JSONPropertyName("shareMal") Boolean shareList,
            Boolean autoHideOpenings,
            Boolean autoHideHighRisk,
            @JSONPropertyName("autoSwitchFavoritedAvatars") Boolean autoSwitchFavoriteAvatars,
            Boolean useWatched,
            Boolean useCompleted,
            Boolean useDropped,
            Boolean autoHideUnwatched
    ){}

    public record FriendEntry (
            Optional<Boolean> avatarProfileImage,
            String avatarName,
            String colorName,
            Optional<Integer> profileEmoteId,
            String name,
            Boolean online,
            String outfitName,
            Boolean optionActive,
            String optionName,
            Optional<PlayerGameState> gameState,
            Integer status
    ){
        public String statusText(){
            return switch(status){
                case 1 -> "Online";
                case 2 -> "Do Not Disturb";
                case 3 -> "Away";
                default -> "Offline";
            };
        }
    }

    public record RankedChampions (
            @JSONPropertyName("1") List<RankedChampion> central,
            @JSONPropertyName("2") List<RankedChampion> western,
            @JSONPropertyName("3") List<RankedChampion> eastern
    ){}
    public record RankedChampion (
            String name,
            Integer position
    ) implements Comparable<RankedChampion>
    {
        @Override
        public int compareTo(@NotNull RankedChampion o) {
            return this.position() - o.position();
        }
    }

    public record EmoteGroup (
            Integer orderNumber,
            List<Emote> emotes
    ){}

    public record Emote (
            Integer tierId,
            String name,
            Integer emoteId
    ){}

    public record CumulativeAvatarDonation(
            Double amount,
            String name
    ){}

    public record ServerStatus (
            String name,
            Boolean online
    ){}

    public record AnimeTag (
            String name,
            Integer id
    ){}

    public record RankedLeaderboard(
            @JSONPropertyName("1") List<RankedLeaderboardEntry> central,
            @JSONPropertyName("2") List<RankedLeaderboardEntry> western,
            @JSONPropertyName("3") List<RankedLeaderboardEntry> eastern
    ){}

    public record RankedLeaderboardEntry(
            Integer score,
            String name,
            Integer position
    ) implements Comparable<RankedLeaderboardEntry>
    {
        @Override
        public int compareTo(@NotNull RankedLeaderboardEntry o) {
            return this.position() - o.position();
        }
    }

    public record RollTarget (
            String fileName,
            String name,
            Integer id
    ){}

    public record XPInfo (
            Double xpPercent, //literally just xpIntoLevel / xpForLevel
            Integer lastGain,
            Integer xpForLevel,
            Integer xpIntoLevel
    ){
        public Integer xpForNextLevel(){
            return xpForLevel - xpIntoLevel;
        }
    }

    public record AnimeGenre (
            String name,
            Integer id
    ){}

    public record AvatarDonation (
            String avatarName,
            Double amount,
            String username
    ){}

    public record CustomEmoji (
            Boolean validated,
            String name,
            Boolean active,
            Integer id,
            Integer type
    ){}

    public record PlayerAvatar (
            AvatarBackground background,
            AvatarPrimary avatar
    ){}

    public record AvatarBackground (
            String avatarName,
            Integer avatarId,
            Integer colorId,
            String outfitName,
            String backgroundVert,
            String backgroundHori
    ){}

    public record AvatarPrimary (
            String colorName,
            Optional<String> editor,
            Integer colorId,
            Boolean active,
            Boolean optionActive,
            String backgroundFileName,
            Integer colorActive,
            String avatarName,
            Integer avatarId,
            String outfitName,
            Integer sizeModifier,
            String optionName,
            Integer characterId
    ){}

    public record RecentEmote (
            Optional<Integer> emoteId, //see EmoteGroup -> Emote
            Optional<Integer> emojiId,
            Optional<String> shortCode
    ){
        public Optional<URI> emojiURI(){
            return emojiId
                    .map(Emojis::getEmojiURI)
                    .filter(Optional::isPresent)
                    .map(Optional::get);
        }
        public Optional<String> shortCodeEmoji(){
            return shortCode
                    .map(Emojis::getEmojiFromShortcode)
                    .filter(Optional::isPresent)
                    .map(Optional::get);
        }
    }

    public record TutorialState (
            Boolean initialShow,
            Boolean firstGameComplete,
            Boolean teamPlayed,
            Boolean avatarCompleted,
            Boolean socialCompleted,
            Boolean livesPlayed,
            Boolean rankedPlayed,
            Boolean lootingPlayed,
            Boolean speedPlayed
    ){}

    public record RewardAlert (
            String fileName,
            String name,
            Integer userRewardAlertId
    ){}

    public record PlayerGameState (
            Integer gameId,
            Boolean isSpectator,
            @JSONPropertyName("private") Boolean isPrivateRoom,
            Boolean inLobby
    ){}
}
