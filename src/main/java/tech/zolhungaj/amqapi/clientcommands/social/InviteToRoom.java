package tech.zolhungaj.amqapi.clientcommands.social;

public record InviteToRoom(
        String target
) implements SocialCommand{
    @Override
    public String command() {
        return "invite to game";
    }
}
