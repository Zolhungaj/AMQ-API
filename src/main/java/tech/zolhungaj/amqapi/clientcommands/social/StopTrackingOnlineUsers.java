package tech.zolhungaj.amqapi.clientcommands.social;

import tech.zolhungaj.amqapi.clientcommands.CommandName;
import tech.zolhungaj.amqapi.clientcommands.EmptyClientCommand;

@EmptyClientCommand
@CommandName("stop tracking online users")
public final class StopTrackingOnlineUsers implements SocialCommand {
}
