package tech.zolhungaj.amqapi.commands;

import org.json.JSONPropertyName;

import java.util.List;
import java.util.Map;

public record LoginComplete(
    boolean gameAdmin,
    List<QuestDescription> questDescriptions,
    List<SavedQuizSetting> savedQuizSettings,
    PatreonBadgeInfo patreonBadgeInfo,
    Object rewardAlert, //TODO get example to figure out type
    Integer driveTotal,
    List<DriveContribution> top5AllTime,
    Boolean displayArtContestPopUp,
    @JSONPropertyName("top5AvatarNominatios") List<AvatarNomination> top5AvatarNominations,
    Integer patreonId,
    Map<String, Integer> avatarUnlockCount,
    Map<String, Map<String, Boolean>> unlockedDesign,
    List<TicketReward> recentTicketRewards


)
implements Command{

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

    private record TicketRewardDescription (
            Integer tierId,
            String name,
            Integer emoteId,
            String colorName,
            String editor,
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
}
