package tech.zolhungaj.amqapi.servercommands.objects;

import java.util.List;
import java.util.Optional;

public record Avatar (
        String colorName,
        Integer outfitId,
        Optional<Integer> patreonTierToUnlock,
        Integer notePrice,
        String lore,
        Boolean limited,
        String artist,
        String badgeName,
        String backgroundVert,
        Boolean active,
        List<AvatarColor> colors,
        Integer defaultColorId,
        String badgeFileName,
        String avatarName,
        Boolean defaultAvatar,
        Integer avatarId,
        String world,
        Optional<Integer> tierId,
        Integer realMoneyPrice,
        String outfitName,
        Boolean exclusive,
        Integer sizeModifier,
        String optionName
){

    public record AvatarColor (
            Optional<String> editor,
            Boolean limited,
            Integer colorId,
            Boolean active,
            String backgroundVert,
            Boolean defaultColor,
            Optional<Integer> tierId,
            Integer price,
            Boolean eventColor, //Always 0?
            Boolean unique,
            String name,
            Boolean exclusive,
            Integer sizeModifier
    ){}

    public record AvatarIdentifier(
            Integer avatarId,
            Integer colorId,
            Boolean optionActive
    ){}
    public record AvatarBackgroundIdentifier(
            Integer avatarId,
            Integer colorId
    ){}
}

