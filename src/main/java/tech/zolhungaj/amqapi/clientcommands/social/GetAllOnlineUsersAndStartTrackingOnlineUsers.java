package tech.zolhungaj.amqapi.clientcommands.social;

import tech.zolhungaj.amqapi.clientcommands.EmptyClientCommand;

public final class GetAllOnlineUsersAndStartTrackingOnlineUsers implements EmptyClientCommand, SocialCommand {
    @Override
    public String command() {
        return "get online users";
    }
}
