package tech.zolhungaj.amqapi.servercommands.globalstate;

import tech.zolhungaj.amqapi.servercommands.Command;
import tech.zolhungaj.amqapi.servercommands.CommandType;

public record PopoutMessage(
	String header,
	String message
)  implements Command {
	@Override
	public String commandName() {
		return CommandType.POPOUT_MESSAGE.commandName;
	}
}
