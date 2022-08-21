package tech.zolhungaj.amqapi.clientcommands.onlineusers;

import tech.zolhungaj.amqapi.clientcommands.ClientCommandType;
import tech.zolhungaj.amqapi.clientcommands.EmptyClientCommand;

public final class StopTrackingOnlineUsers extends EmptyClientCommand {
    public StopTrackingOnlineUsers() {
        super(ClientCommandType.STOP_TRACKING_ONLINE_USERS);
    }
}
