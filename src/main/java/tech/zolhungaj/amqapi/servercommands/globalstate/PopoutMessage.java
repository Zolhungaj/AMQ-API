package tech.zolhungaj.amqapi.servercommands.globalstate;

import tech.zolhungaj.amqapi.servercommands.Command;
import tech.zolhungaj.amqapi.servercommands.CommandTypeOld;

public record PopoutMessage(
	String header,
	String message
)  implements Command {
	@Override
	public String commandName() {
		return CommandTypeOld.POPOUT_MESSAGE.commandName;
	}
}
