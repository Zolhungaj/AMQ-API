package tech.zolhungaj.amqapi.servercommands.globalstate;

import tech.zolhungaj.amqapi.servercommands.CommandType;

@CommandType("popout message")
public record PopoutMessage(
	String header,
	String message
){}
