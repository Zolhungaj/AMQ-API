package tech.zolhungaj.amqapi.clientcommands.lobby;

import tech.zolhungaj.amqapi.clientcommands.CommandName;
import tech.zolhungaj.amqapi.clientcommands.EmptyClientCommand;

@CommandName("host afk")
@EmptyClientCommand
public final class MarkSelfAsAfkAndSurrenderHostStatusToSomeoneElse implements LobbyCommand {
}
