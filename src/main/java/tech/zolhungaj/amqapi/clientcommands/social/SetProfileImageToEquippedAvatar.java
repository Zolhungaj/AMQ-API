package tech.zolhungaj.amqapi.clientcommands.social;

import com.fasterxml.jackson.annotation.JsonProperty;
import tech.zolhungaj.amqapi.clientcommands.CommandName;

@CommandName("player profile set image")
public final class SetProfileImageToEquippedAvatar implements SocialCommand{
    @JsonProperty("avatarImage")
    public boolean getAvatarImage() {
        return true;
    }
    @JsonProperty("emoteId")
    Object getEmoteId(){
        return null;
    }
}
