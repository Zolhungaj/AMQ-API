package tech.zolhungaj.amqapi.servercommands.objects;

import java.util.List;
import java.util.Optional;

public record StoreAvatar(
        String colorName,
        int outfitId,
        Optional<Integer> patreonTierToUnlock,
        int notePrice,
        String lore,
        boolean limited,
        String artist,
        String badgeName,
        String backgroundVert,
        boolean active,
        List<AvatarColor> colors,
        int defaultColorId,
        String badgeFileName,
        String avatarName,
        boolean defaultAvatar,
        int avatarId,
        String world,
        Optional<Integer> tierId,
        int realMoneyPrice,
        String outfitName,
        boolean exclusive,
        int sizeModifier,
        String optionName
){

    public record AvatarColor (
            Optional<String> editor,
            boolean limited,
            int colorId,
            boolean active,
            String backgroundVert,
            boolean defaultColor,
            Optional<Integer> tierId,
            int price,
            boolean eventColor, //Always 0?
            boolean unique,
            String name,
            boolean exclusive,
            int sizeModifier
    ){}
}

