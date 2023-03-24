package tech.zolhungaj.amqapi.clientcommands.roombrowser;

import tech.zolhungaj.amqapi.clientcommands.ClientCommand;

public sealed interface RoomBrowserCommand extends ClientCommand permits HostRoom {
    @Override
    default String type() {
        return "roombrowser";
    }
}
