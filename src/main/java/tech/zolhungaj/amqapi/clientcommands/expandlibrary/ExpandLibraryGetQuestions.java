package tech.zolhungaj.amqapi.clientcommands.expandlibrary;

import tech.zolhungaj.amqapi.clientcommands.AbstractClientCommand;
import tech.zolhungaj.amqapi.clientcommands.ClientCommandType;

public final class ExpandLibraryGetQuestions extends AbstractClientCommand {
    public ExpandLibraryGetQuestions(){
        super(ClientCommandType.EXPAND_LIBRARY_OPEN);
    }
}
