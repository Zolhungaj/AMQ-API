package tech.zolhungaj.amqapi.clientcommands.social;

public record SetProfileShowcaseBadge(
        int badgeId,
        int slotNumber
) implements SocialCommand{
    @Override
    public String command() {
        return "player profile show badge";
    }
}
