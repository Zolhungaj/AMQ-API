package tech.zolhungaj.amqapi.clientcommands.social;

import com.fasterxml.jackson.annotation.JsonProperty;

public sealed interface SetProfileImageCommand extends SocialCommand permits SetProfileImageToEmote, SetProfileImageToEquippedAvatar {
    @JsonProperty("avatarImage")
    default Boolean getAvatarImage(){
        return null;
    }

    @JsonProperty("emoteId")
    default Integer getEmoteId(){
        return null;
    }
    @Override
    default String command() {
        return "player profile set image";
    }
}
