package tech.zolhungaj.amqapi.servercommands;

public record FriendNameChange(
        String newName,
        String oldName
) implements Command{
    @Override
    public String getCommandName() {
        return CommandType.FRIEND_NAME_UPDATE.commandName;
    }
}
