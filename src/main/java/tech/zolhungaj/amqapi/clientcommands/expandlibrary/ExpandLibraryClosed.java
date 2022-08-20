package tech.zolhungaj.amqapi.clientcommands.expandlibrary;

import tech.zolhungaj.amqapi.clientcommands.AbstractClientCommand;
import tech.zolhungaj.amqapi.clientcommands.ClientCommandType;

public final class ExpandLibraryClosed extends AbstractClientCommand {
    public ExpandLibraryClosed(){
        super(ClientCommandType.EXPAND_LIBRARY_CLOSE);
    }
}
