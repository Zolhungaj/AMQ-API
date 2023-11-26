package tech.zolhungaj.amqapi.clientcommands.lobby;

import tech.zolhungaj.amqapi.clientcommands.CommandName;
import tech.zolhungaj.amqapi.clientcommands.EmptyClientCommand;

@EmptyClientCommand
@CommandName("change to player")
public final class MoveSelfToPlayer implements LobbyCommand {
}
