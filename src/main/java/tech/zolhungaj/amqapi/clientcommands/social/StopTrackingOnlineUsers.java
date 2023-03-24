package tech.zolhungaj.amqapi.clientcommands.social;

import tech.zolhungaj.amqapi.clientcommands.EmptyClientCommand;

public final class StopTrackingOnlineUsers implements EmptyClientCommand, SocialCommand {
    @Override
    public String command() {
        return "stop tracking online users";
    }
}
