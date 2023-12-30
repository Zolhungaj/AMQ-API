package tech.zolhungaj.amqapi.servercommands.globalstate;

import com.squareup.moshi.Json;
import tech.zolhungaj.amqapi.servercommands.CommandType;

import java.util.Optional;

@CommandType("friend profile image change")
public record FriendProfileImageChange(
        @Json(name = "name") String playerName,
        AvatarInfo profileImage
){
    public record AvatarInfo(
            @Json(name = "profileEmoteId") Optional<Integer> emoteId,
            String avatarName,
            String outfitName,
            String optionName,
            Boolean optionActive,
            String colorName,
            Optional<String> editor,
            int colorId,
            Boolean active,
            String backgroundFileName,
            Boolean colorActive,
            int avatarId,
            int sizeModifier,
            int characterId
    ) {}
}
