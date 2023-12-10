package tech.zolhungaj.amqapi.servercommands.globalstate;

import tech.zolhungaj.amqapi.servercommands.Command;
import tech.zolhungaj.amqapi.servercommands.CommandTypeOld;

public record SelfNameChange(String newName) implements Command {
    @Override
    public String commandName() {
        return CommandTypeOld.SELF_NAME_UPDATE.commandName;
    }
}
