package tech.zolhungaj.amqapi.clientcommands.lobby;

public record SetSelfReadyStatus(
    boolean ready
) implements LobbyCommand{
    @Override
    public String command() {
        return "set ready";
    }
}
