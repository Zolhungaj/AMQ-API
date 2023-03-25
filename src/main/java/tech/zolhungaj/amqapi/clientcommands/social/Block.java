package tech.zolhungaj.amqapi.clientcommands.social;

public record Block(
        String target
) implements SocialCommand{
    @Override
    public String command() {
        return "block player";
    }
}
