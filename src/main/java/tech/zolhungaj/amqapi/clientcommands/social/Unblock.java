package tech.zolhungaj.amqapi.clientcommands.social;

public record Unblock(
        String target
) implements SocialCommand{
    @Override
    public String command() {
        return "unblock player";
    }
}
