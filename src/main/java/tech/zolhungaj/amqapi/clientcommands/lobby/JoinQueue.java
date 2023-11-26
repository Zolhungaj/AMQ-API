package tech.zolhungaj.amqapi.clientcommands.lobby;

import tech.zolhungaj.amqapi.clientcommands.CommandName;
import tech.zolhungaj.amqapi.clientcommands.EmptyClientCommand;

@EmptyClientCommand
@CommandName("join game queue")
public final class JoinQueue implements LobbyCommand {
}
