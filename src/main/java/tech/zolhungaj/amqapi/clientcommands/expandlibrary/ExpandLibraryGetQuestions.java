package tech.zolhungaj.amqapi.clientcommands.expandlibrary;

import tech.zolhungaj.amqapi.clientcommands.ClientCommandType;
import tech.zolhungaj.amqapi.clientcommands.EmptyClientCommand;

public final class ExpandLibraryGetQuestions extends EmptyClientCommand {
    public ExpandLibraryGetQuestions(){
        super(ClientCommandType.OPEN_EXPAND_LIBRARY);
    }
}