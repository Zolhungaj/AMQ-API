package tech.zolhungaj.amqapi.servercommands.objects;

import com.squareup.moshi.Json;

import java.util.List;
import java.util.Optional;

public record StoreAvatar(
        String colorName,
        int outfitId,
        Optional<Integer> patreonTierToUnlock,
        int notePrice,
        String lore,
        Boolean limited,
        String artist,
        String badgeName,
        @Json(name = "backgroundVert")
        String backgroundVertical,
        Boolean active,
        List<AvatarColor> colors,
        int defaultColorId,
        String badgeFileName,
        String avatarName,
        Boolean defaultAvatar,
        int avatarId,
        String world,
        Optional<Integer> tierId,
        int realMoneyPrice,
        String outfitName,
        Boolean exclusive,
        int sizeModifier,
        String optionName
){

    public record AvatarColor (
            Optional<String> editor,
            Boolean limited,
            int colorId,
            Boolean active,
            @Json(name = "backgroundVert")
            String backgroundVertical,
            Boolean defaultColor,
            Optional<Integer> tierId,
            int price,
            Boolean eventColor, //Always 0?
            Boolean unique,
            String name,
            Boolean exclusive,
            int sizeModifier
    ){}
}

