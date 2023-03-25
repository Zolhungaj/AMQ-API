package tech.zolhungaj.amqapi.clientcommands.social;

public record ClearProfileShowcaseBadge(
        int slotNumber
) implements SocialCommand{
    @Override
    public String command() {
        return "player profile clear badge";
    }
}
