package tech.zolhungaj.amqapi.clientcommands.social;

public record RemoveFriend(
        String target
) implements SocialCommand{
    @Override
    public String command() {
        return "remove friend";
    }
}
