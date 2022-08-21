package tech.zolhungaj.amqapi.clientcommands.expandlibrary;

import tech.zolhungaj.amqapi.clientcommands.ClientCommandType;
import tech.zolhungaj.amqapi.clientcommands.EmptyClientCommand;

public final class ExpandLibraryClose extends EmptyClientCommand {
    public ExpandLibraryClose(){
        super(ClientCommandType.CLOSE_EXPAND_LIBRARY);
    }
}
