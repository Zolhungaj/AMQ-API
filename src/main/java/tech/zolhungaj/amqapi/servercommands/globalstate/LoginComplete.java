package tech.zolhungaj.amqapi.servercommands.globalstate;

import com.squareup.moshi.Json;

import tech.zolhungaj.amqapi.servercommands.Command;
import tech.zolhungaj.amqapi.servercommands.CommandType;
import tech.zolhungaj.amqapi.servercommands.objects.*;
import tech.zolhungaj.amqapi.constants.AmqRanked;
import tech.zolhungaj.amqapi.constants.Emojis;

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
        Optional<RewardAlert> rewardAlert,
        double driveTotal,
        List<AvatarDriveContribution> top5AllTime,
        Boolean displayArtContestPopUp,
        @Json(name = "top5AvatarNominatios") List<AvatarDriveNomination> top5AvatarNominations,
        Optional<Integer> patreonId,
        Map<String, Integer> avatarUnlockCount,
        Map<String, Map<String, Boolean>> unlockedDesigns,
        List<TicketReward> recentTicketRewards,
        RankedState rankedState,
        List<SuperAvatar> defaultAvatars,
        int backerLevel,
        UserSettings settings,
        int level,
        List<Integer> unlockedEmoteIds,
        @Json(name = "malName") Optional<String> mal,
        Optional<String> aniList,
        Optional<String> kitsu,
        Optional<String> malLastUpdate,
        Optional<String> aniListLastUpdate,
        Optional<String> kitsuLastUpdate,
        List<FriendEntry> friends,
        RankedChampions rankedChampions,
        int nameChangeTokens,
        List<String> blockedPlayers,
        List<EmoteGroup> emoteGroups,
        int tickets,
        @Json(name = "top5Montly") List<AvatarDriveContribution> top5Monthly,
        List<ServerStatus> serverStatuses,
        Boolean topAdmin,
        Boolean useRomajiNames,
        int questTokenProgress,
        @Json(name = "tagInfo") List<AnimeTag> tags,
        RankedLeaderboard rankedLeaderboards,
        List<RollTarget> rollTargets,
        XPInfo xpInfo,
        int credits,
        @Json(name = "genreInfo") List<AnimeGenre> genres,
        int tutorial,
        @Json(name = "canReconnectGame") Boolean canReconnectToGame,
        List<AvatarDonation> recentDonations,
        int avatarTokens,
        Boolean freeDonation,
        Map<String, Integer> characterUnlockCount,
        List<CustomEmoji> customEmojis,
        PlayerAvatar avatar,
        Optional<Boolean> patreonDesynced,
        int rhythm,
        List<String> videoHostNames,
        Boolean twitterClaimed,
        List<FavoriteAvatar> favoriteAvatars,
        List<AvatarDriveContribution> top5Weekly,
        Boolean discordClaimed,
        int expandCount,
        List<RecentEmote> recentEmotes,
        Boolean saleTax,
        @Json(name = "self") String selfName,
        int badgeLevel,
        Boolean guestAccount,
        TutorialState tutorialState,
        List<NexusBuff> nexusBuffs,
        @Json(name = "canReconnectNexus") Boolean canReconnectToNexus,
        @Json(name = "nexusStatBaseMax") int nexusStatsBaseMaximum,
        @Json(name ="nexusAccess") Boolean hasNexusAccess
)
implements Command {
    public Optional<Instant> aniListLastUpdateInstant(){
        return aniListLastUpdate.map(Instant::parse);
    }
    public Optional<Instant> kitsuLastUpdateInstant(){
        return kitsuLastUpdate.map(Instant::parse);
    }
    public Optional<Instant> malLastUpdateInstant(){
        return malLastUpdate.map(Instant::parse);
    }



    @Override
    public String commandName() {
        return CommandType.LOGIN_COMPLETE.commandName;
    }

    public record QuestDescription(
            int ticketReward,
            int questId,
            int targetState,
            int weekSlot,
            String name,
            String description,
            int state,
            int noteReward
    ){}

    public record SavedQuizSetting (
            String name,
            String settingString,
            int id
    ){}

    public record PatreonBadgeInfo (
            PatreonBadge next,
            PatreonBadge current
    ){}

    public record PatreonBadge (
            Boolean special,
            String fileName,
            String name,
            int id,
            int type,
            String unlockDescription
    ){}

    public record TicketReward (
            int tier,
            TicketRewardDescription description,
            String type,
            int rhythm
    ){}
    
    public record TicketRewardDescription (
            Optional<Integer> tierId,
            @Json(name = "name")
            Optional<String> rewardName,
            Optional<Integer> emoteId,
            Optional<String> colorName,
            Optional<String> editor,
            Optional<Integer> colorId,
            Optional<Boolean> active,
            Optional<Boolean> optionActive,
            Optional<String> backGroundFileName,
            Optional<Boolean> colorActive,
            Optional<String> avatarName,
            Optional<Integer> avatarId,
            Optional<String> outfitName,
            Optional<Integer> sizeModifier,
            Optional<String> optionName,
            Optional<Integer> characterId
    ){
        public TicketRewardDescription{
            /*
             * Until a <a href="https://github.com/square/moshi/pull/1412">fix is merged</a>
             * all objects here are nullable, and this is a workaround to make them consistently Optional
             */
            if (tierId == null) tierId = Optional.empty();
            if (rewardName == null) rewardName = Optional.empty();
            if (emoteId == null) emoteId = Optional.empty();
            if (colorName == null) colorName = Optional.empty();
            if (editor == null) editor = Optional.empty();
            if (colorId == null) colorId = Optional.empty();
            if (active == null) active = Optional.empty();
            if (optionActive == null) optionActive = Optional.empty();
            if (backGroundFileName == null) backGroundFileName = Optional.empty();
            if (colorActive == null) colorActive = Optional.empty();
            if (avatarName == null) avatarName = Optional.empty();
            if (avatarId == null) avatarId = Optional.empty();
            if (outfitName == null) outfitName = Optional.empty();
            if (sizeModifier == null) sizeModifier = Optional.empty();
            if (optionName == null) optionName = Optional.empty();
            if (characterId == null) characterId = Optional.empty();
        }
    }

    public record SuperAvatar(
            int characterId,
            List<StoreAvatar> avatars
    ){}
    public record UserSettings (
            Boolean autoHideInserts,
            Boolean disableEmojis,
            int animeList,
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
            @Json(name = "shareMal") Boolean shareList,
            Boolean autoHideOpenings,
            Boolean autoHideHighRisk,
            @Json(name = "autoSwitchFavoritedAvatars") Boolean autoSwitchFavoriteAvatars,
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
            PlayerStatus status
    ){
        public FriendEntry{
            if (avatarProfileImage == null) avatarProfileImage = Optional.empty();
            if (profileEmoteId == null) profileEmoteId = Optional.empty();
            if (gameState == null) gameState = Optional.empty();
        }
    }

    public record RankedChampions (
            @Json(name = "1") List<RankedChampion> central,
            @Json(name = "2") List<RankedChampion> western,
            @Json(name = "3") List<RankedChampion> eastern
    ){}

    public record RankedState (
            ActiveRankedGameModes games,
            @Json(name = "serieId") Optional<Integer> seriesId,
            int state
    ){
        public RankedState{
            if (seriesId == null) seriesId = Optional.empty();
        }
        public AmqRanked.RANKED_STATE getRankedState(){
            return AmqRanked.RANKED_STATE.forId(state);
        }
        public Optional<AmqRanked.GAME_SERIES> getRankedSeries(){
            return seriesId.map(AmqRanked.GAME_SERIES::forId);
        }
    }

    public record ActiveRankedGameModes (
            Boolean expert,
            Boolean novice
    ){}


    public record EmoteGroup (
            int orderNumber,
            List<Emote> emotes
    ){}

    public record ServerStatus (
            String name,
            Boolean online
    ){}

    public record AnimeTag (
            String name,
            int id
    ){}

    public record RankedLeaderboard(
            @Json(name = "1") List<RankedLeaderboardEntry> central,
            @Json(name = "2") List<RankedLeaderboardEntry> western,
            @Json(name = "3") List<RankedLeaderboardEntry> eastern
    ){}

    public record RollTarget (
            String fileName,
            String name,
            int id
    ){}

    public record XPInfo (
            double xpPercent, //literally just xpIntoLevel / xpForLevel
            int lastGain,
            int xpForLevel,
            int xpIntoLevel
    ){
        public int xpForNextLevel(){
            return xpForLevel - xpIntoLevel;
        }
    }

    public record AnimeGenre (
            String name,
            int id
    ){}

    public record AvatarDonation (
            String avatarName,
            double amount,
            String username
    ){}

    public record CustomEmoji (
            Boolean validated,
            String name,
            Boolean active,
            int id,
            int type
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
            Boolean rankedCompleted,
            Boolean lootingPlayed,
            Boolean speedPlayed
    ){}

    public record RewardAlert (
            String fileName,
            String name,
            int userRewardAlertId
    ){}



    public record FavoriteAvatar(
            int favoriteId,
            @Json(name = "avatar") AvatarIdentifier avatarIdentifier,
            @Json(name = "background") AvatarBackgroundIdentifier avatarBackgroundIdentifier
    ){}
}
