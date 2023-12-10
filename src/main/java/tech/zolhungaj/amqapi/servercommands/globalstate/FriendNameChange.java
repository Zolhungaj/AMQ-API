package tech.zolhungaj.amqapi.servercommands.globalstate;

import tech.zolhungaj.amqapi.servercommands.Command;
import tech.zolhungaj.amqapi.servercommands.CommandTypeOld;

public record FriendNameChange(
        String newName,
        String oldName
) implements Command {
    @Override
    public String commandName() {
        return CommandTypeOld.FRIEND_NAME_UPDATE.commandName;
    }
}
