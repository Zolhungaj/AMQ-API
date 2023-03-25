package tech.zolhungaj.amqapi.clientcommands.social;

public record SetProfileChatBadge(
        int badgeId
) implements SocialCommand{
    @Override
    public String command() {
        return "player profile set chat badge";
    }
}
