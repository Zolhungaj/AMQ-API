package tech.zolhungaj.amqapi.clientcommands.social;

import com.fasterxml.jackson.annotation.JsonProperty;
import tech.zolhungaj.amqapi.clientcommands.CommandName;

@CommandName("player profile set image")
public record SetProfileImageToEmote(int emoteId) implements SocialCommand {
    @JsonProperty("avatarImage")
    public Object getAvatarImage(){
        return null;
    }
}
