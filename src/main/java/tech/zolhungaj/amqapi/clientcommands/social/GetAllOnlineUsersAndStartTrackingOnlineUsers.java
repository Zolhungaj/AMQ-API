package tech.zolhungaj.amqapi.clientcommands.social;

import tech.zolhungaj.amqapi.clientcommands.CommandName;
import tech.zolhungaj.amqapi.clientcommands.EmptyClientCommand;

@EmptyClientCommand
@CommandName("get online users")
public final class GetAllOnlineUsersAndStartTrackingOnlineUsers implements SocialCommand {
}
