package tech.zolhungaj.amqapi.servercommands.globalstate;

import tech.zolhungaj.amqapi.servercommands.Command;
import tech.zolhungaj.amqapi.servercommands.CommandType;

public record FriendNameChange(
        String newName,
        String oldName
) implements Command {
    @Override
    public String commandName() {
        return CommandType.FRIEND_NAME_UPDATE.commandName;
    }
}
