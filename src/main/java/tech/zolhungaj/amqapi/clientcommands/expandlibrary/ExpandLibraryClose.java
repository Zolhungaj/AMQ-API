package tech.zolhungaj.amqapi.clientcommands.expandlibrary;

import tech.zolhungaj.amqapi.clientcommands.EmptyClientCommand;

public final class ExpandLibraryClose implements EmptyClientCommand, ExpandLibraryCommand {
    @Override
    public String command() {
        return "expandLibrary closed";
    }
}
