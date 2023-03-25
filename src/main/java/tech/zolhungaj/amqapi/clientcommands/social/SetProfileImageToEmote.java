package tech.zolhungaj.amqapi.clientcommands.social;

public record SetProfileImageToEmote(int emoteId) implements SetProfileImageCommand{
    @Override
    public Integer getEmoteId() {
        return emoteId;
    }
}
