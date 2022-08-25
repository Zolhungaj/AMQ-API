package tech.zolhungaj.amqapi.servercommands;

import com.squareup.moshi.Json;

import java.util.Optional;

public record FriendProfileImageChange(
        @Json(name = "name") String playerName,
        AvatarInfo profileImage
) implements Command{
    @Override
    public String getCommandName() {
        return CommandType.FRIEND_PROFILE_IMAGE_UPDATE.commandName;
    }

    public record AvatarInfo(
            @Json(name = "profileEmoteId") Optional<Integer> emoteId,
            String avatarName,
            String outfitName,
            String optionName,
            Boolean optionActive,
            String colorName,
            Optional<String> editor,
            Integer colorId,
            Boolean active,
            String backgroundFileName,
            Boolean colorActive,
            Integer avatarId,
            Integer sizeModifier,
            Integer characterId
    ) {}
}
