package tech.zolhungaj.amqapi.servercommands.objects;

import com.squareup.moshi.Json;

import java.util.Optional;

public record PlayerAvatar(
        AvatarDescription avatar,
        BackgroundDescription background
) {
    public record AvatarDescription(
            int avatarId,
            int colorId,
            int characterId,
            String avatarName,
            String outfitName,
            String colorName,
            @Json(name = "backgroundFileName") String backgroundFilename,
            Boolean colorActive,
            Optional<String> editor,
            int sizeModifier,
            String optionName,
            Boolean optionActive,
            Boolean active
    ) {
    }

    public record BackgroundDescription(
            int avatarId,
            int colorId,
            String avatarName,
            String outfitName,
            String backgroundHori,
            String backgroundVert
    ) {
    }
}
