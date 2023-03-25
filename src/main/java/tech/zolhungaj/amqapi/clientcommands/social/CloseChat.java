package tech.zolhungaj.amqapi.clientcommands.social;

public record CloseChat(
        String target
) implements SocialCommand{
    @Override
    public String command() {
        return "closed chat";
    }
}
