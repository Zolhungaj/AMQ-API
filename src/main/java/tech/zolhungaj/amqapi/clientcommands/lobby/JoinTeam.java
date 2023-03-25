package tech.zolhungaj.amqapi.clientcommands.lobby;

public record JoinTeam(
        int teamNumber
) implements LobbyCommand{
    @Override
    public String command() {
        return "join team";
    }
}
