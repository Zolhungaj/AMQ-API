package tech.zolhungaj.amqapi.clientcommands.social;

public record SendFriendRequest(
        String target
) implements SocialCommand{
    @Override
    public String command() {
        return "friend request";
    }
}
