package tech.zolhungaj.amqapi.clientcommands.social;

public record FriendRequestResponse(
        String target,
        boolean accept
) implements SocialCommand {
    @Override
    public String command() {
        return "friend request response";
    }
}
