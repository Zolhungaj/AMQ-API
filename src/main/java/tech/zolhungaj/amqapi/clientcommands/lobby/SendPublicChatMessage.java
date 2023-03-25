package tech.zolhungaj.amqapi.clientcommands.lobby;

public record SendPublicChatMessage(
        String message
) implements SendChatMessage {
    @Override
    public boolean isTeamMessage(){
        return false;
    }
}
