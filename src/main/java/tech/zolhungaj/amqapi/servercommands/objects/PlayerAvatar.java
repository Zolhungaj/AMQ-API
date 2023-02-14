package tech.zolhungaj.amqapi.servercommands.objects;

import com.squareup.moshi.Json;

import java.util.Optional;

public record PlayerAvatar(
        AvatarDescription avatar,
        BackgroundDescription background
) {
    public record AvatarDescription(
            Integer avatarId,
            Integer colorId,
            Integer characterId,
            String avatarName,
            String outfitName,
            String colorName,
            @Json(name = "backgroundFileName") String backgroundFilename,
            Boolean colorActive,
            Optional<String> editor,
            Integer sizeModifier,
            String optionName,
            Boolean optionActive,
            Boolean active
    ) {
    }

    public record BackgroundDescription(
            Integer avatarId,
            Integer colorId,
            String avatarName,
            String outfitName,
            String backgroundHori,
            String backgroundVert
    ) {
    }
}
