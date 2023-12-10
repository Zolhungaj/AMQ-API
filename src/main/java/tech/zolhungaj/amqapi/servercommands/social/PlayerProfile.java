package tech.zolhungaj.amqapi.servercommands.social;

import com.squareup.moshi.Json;
import tech.zolhungaj.amqapi.servercommands.Command;
import tech.zolhungaj.amqapi.servercommands.CommandTypeOld;
import tech.zolhungaj.amqapi.sharedobjects.AnimeList;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public record PlayerProfile(
        @Json(name = "name")
        String nickname,
        String originalName,
        @Json(name = "guessPercent")
        GuessPercent guessPercent,
        @Json(name = "badges")
        List<DisplayedPlayerBadge> displayedBadges,
        @Json(name = "allBadges")
        List<AvailablePlayerBadge> availablePlayerBadges,
        @Json(name = "chatGlowActive")
        Optional<Boolean> chatGlowActive,
        @Json(name = "chatColorActive")
        Optional<Boolean> chatColorActive,
        @Json(name = "rankedChatGlowUnlocked")
        Optional<Boolean> rankedChatGlowUnlocked,
        @Json(name = "rankedChatColorUnlocked")
        Optional<Boolean> rankedChatColorUnlocked,
        int level,
        @Json(name = "avatarProfileImage")
        Boolean avatarProfileImage,
        @Json(name = "profileEmoteId")
        Optional<Integer> profileImageIdentifiedByEmoteId,
        @Json(name = "avatar")
        Optional<ProfileAvatar> profileImageFromAvatar,
        @Json(name = "creationDate")
        CreationDate creationDate,
        @Json(name = "list")
        DisplayedAnimeList displayedAnimeList,
        @Json(name = "songCount")
        SongCount songCount
) implements Command {

    public PlayerProfile{
        if(chatGlowActive == null){
            chatGlowActive = Optional.empty();
        }
        if(chatColorActive == null){
            chatColorActive = Optional.empty();
        }
        if(rankedChatGlowUnlocked == null){
            rankedChatGlowUnlocked = Optional.empty();
        }
        if(rankedChatColorUnlocked == null){
            rankedChatColorUnlocked = Optional.empty();
        }
    }
    @Override
    public String commandName() {
        return CommandTypeOld.PLAYER_PROFILE.commandName;
    }

    public record ProfileAvatar(
            String avatarName,
            String colorName,
            String outfitName,
            @Json(name = "optionActive")
            int optionActive,
            String optionName
    ) {
    }

    public record CreationDate(
            @Json(name = "hidden")
            boolean isHidden,
            @Json(name = "adminView")
            boolean isAdminView,
            @Json(name = "value")
            Optional<LocalDate> value
    ) {}

    public record GuessPercent(
            @Json(name = "hidden")
            boolean isHidden,
            @Json(name = "adminView")
            boolean isAdminView,
            @Json(name = "value")
            Optional<Double> value
    ) {}
    public record SongCount(
            @Json(name = "hidden")
            boolean isHidden,
            @Json(name = "adminView")
            boolean isAdminView,
            @Json(name = "value")
            Optional<Integer> value
    ) {}

    public record DisplayedAnimeList(
            @Json(name = "hidden")
            boolean isHidden,
            @Json(name = "adminView")
            boolean isAdminView,
            @Json(name = "listId")
            Optional<AnimeList> listSite,
            @Json(name = "listUserUrl")
            Optional<String> listSiteUserUrl,
            @Json(name = "listUser")
            Optional<String> listSiteUsername
    ) {
    }

    public record DisplayedPlayerBadge(
            @Json(name = "special")
            boolean isSpecial,
            @Json(name = "fileName")
            String filename,
            @Json(name = "name")
            String badgeName,
            @Json(name = "id")
            int badgeId,
            @Json(name = "slot")
            int slotId,
            @Json(name = "type")
            int badgeType,
            String unlockDescription
    ) {}

    public record AvailablePlayerBadge(
            @Json(name = "unlocked")
            Boolean isUnlocked,
            Optional<Boolean> showInChat,
            @Json(name = "slot")
            Optional<Integer> slotId,
            @Json(name = "special")
            boolean isSpecial,
            @Json(name = "fileName")
            String filename,
            @Json(name = "name")
            String badgeName,
            @Json(name = "id")
            int badgeId,
            @Json(name = "type")
            int badgeType,
            String unlockDescription
    ) {
        public AvailablePlayerBadge{
            if(isUnlocked == null){
                isUnlocked = false;
            }
            if(showInChat == null){
                showInChat = Optional.empty();
            }
            if(slotId == null){
                slotId = Optional.empty();
            }
        }
    }
}
