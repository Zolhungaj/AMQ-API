package tech.zolhungaj.amqapi.clientcommands.expandlibrary;

import lombok.*;
import tech.zolhungaj.amqapi.clientcommands.AbstractClientCommand;
import tech.zolhungaj.amqapi.clientcommands.ClientCommandType;

@Getter
@Setter
@ToString
public final class ExpandLibrarySubmit extends AbstractClientCommand {
    public ExpandLibrarySubmit(){
        super(ClientCommandType.EXPAND_LIBRARY_SUBMIT);
    }
}
