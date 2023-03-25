package tech.zolhungaj.amqapi.clientcommands.social;

public record OpenChat(
        String target
) implements SocialCommand{
    @Override
    public String command() {
        return "opened chat";
    }
}
