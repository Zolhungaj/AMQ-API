package tech.zolhungaj.amqapi.servercommands.store;

import com.squareup.moshi.Json;
import tech.zolhungaj.amqapi.servercommands.Command;
import tech.zolhungaj.amqapi.servercommands.CommandType;
import tech.zolhungaj.amqapi.servercommands.objects.Emote;
import tech.zolhungaj.amqapi.servercommands.objects.PlayerAvatar;

import java.util.List;

public record TicketRollResult(
        List<? extends Reward> rewardList,
        int ticketCount,
        @Json(name = "avatarTokens")
        int newCurrentAvatarTokens
) implements Command {
    public sealed interface Reward {
        @Json(name = "type")
        String rewardType();//avatar, color, emote(?)
        int tier();
        int rhythmReward();
    }
    public record SkinReward(
            @Json(name = "type")
            String rewardType,
            int tier,
            PlayerAvatar.AvatarDescription description,
            @Json(name = "rhythm")
            int rhythmReward

    )implements Reward {}
    public record ColorReward(
            @Json(name = "type")
            String rewardType,
            int tier,
            PlayerAvatar.AvatarDescription description,
            @Json(name = "rhythm")
            int rhythmReward

    )implements Reward {}
    public record EmoteReward(
            @Json(name = "type")
            String rewardType,
            int tier,
            Emote description,
            @Json(name = "rhythm")
            int rhythmReward

    )implements Reward {}
    @Override
    public String commandName() {
        return CommandType.TICKET_ROLL_RESULT.commandName;
    }


}
