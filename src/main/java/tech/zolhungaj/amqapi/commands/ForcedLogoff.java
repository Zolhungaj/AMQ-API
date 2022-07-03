package tech.zolhungaj.amqapi.commands;

public record ForcedLogoff(
        String reason
)implements Command {

    @Override
    public String getCommandName() {
        return CommandType.FORCED_LOGOFF.commandName;
    }
}
