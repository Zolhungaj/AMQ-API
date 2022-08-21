package tech.zolhungaj.amqapi.clientcommands.onlineusers;

import tech.zolhungaj.amqapi.clientcommands.ClientCommandType;
import tech.zolhungaj.amqapi.clientcommands.EmptyClientCommand;

public class GetAllOnlineUsersAndStartTrackingOnlineUsers extends EmptyClientCommand {
    protected GetAllOnlineUsersAndStartTrackingOnlineUsers() {
        super(ClientCommandType.START_TRACKING_ONLINE_USERS);
    }
}
