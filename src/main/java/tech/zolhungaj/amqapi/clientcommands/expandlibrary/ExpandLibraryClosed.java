package tech.zolhungaj.amqapi.clientcommands.expandlibrary;

import tech.zolhungaj.amqapi.clientcommands.ClientCommandType;
import tech.zolhungaj.amqapi.clientcommands.EmptyClientCommand;

public final class ExpandLibraryClosed extends EmptyClientCommand {
    public ExpandLibraryClosed(){
        super(ClientCommandType.EXPAND_LIBRARY_CLOSE);
    }
}
