package tech.zolhungaj.amqapi.clientcommands.social;

public record SendDirectMessage(String target, String message) implements SocialCommand {
    @Override
    public String command() {
        return "chat message";
    }
}
