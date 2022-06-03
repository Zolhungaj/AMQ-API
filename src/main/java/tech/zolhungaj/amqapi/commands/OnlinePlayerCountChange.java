package tech.zolhungaj.amqapi.commands;

public record OnlinePlayerCountChange(
        Integer count
) implements Command{


    @Override
    public String getName() {
        return Commands.ONLINE_PLAYERS.name();
    }
}
