package tech.zolhungaj.amqapi.clientcommands.expandlibrary;

import tech.zolhungaj.amqapi.clientcommands.CommandName;
import tech.zolhungaj.amqapi.clientcommands.EmptyClientCommand;

@EmptyClientCommand
@CommandName("expandLibrary questions")
public final class LoadExpandLibraryAndStartListeningForChanges implements ExpandLibraryCommand {
}
