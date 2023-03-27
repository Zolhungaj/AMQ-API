package tech.zolhungaj.amqapi.servercommands.globalstate;

import com.squareup.moshi.Json;
import tech.zolhungaj.amqapi.servercommands.Command;
import tech.zolhungaj.amqapi.servercommands.CommandType;

import java.util.Optional;

public record FriendProfileImageChange(
        @Json(name = "name") String playerName,
        AvatarInfo profileImage
) implements Command {
    @Override
    public String commandName() {
        return CommandType.FRIEND_PROFILE_IMAGE_UPDATE.commandName;
    }

    public record AvatarInfo(
            @Json(name = "profileEmoteId") Optional<Integer> emoteId,
            String avatarName,
            String outfitName,
            String optionName,
            boolean optionActive,
            String colorName,
            Optional<String> editor,
            int colorId,
            boolean active,
            String backgroundFileName,
            boolean colorActive,
            int avatarId,
            int sizeModifier,
            int characterId
    ) {}
}
