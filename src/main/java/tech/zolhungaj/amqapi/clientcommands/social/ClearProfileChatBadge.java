package tech.zolhungaj.amqapi.clientcommands.social;

public record ClearProfileChatBadge(
        int badgeId
) implements SocialCommand{
    @Override
    public String command() {
        return "player profile clear chat badge";
    }
}
