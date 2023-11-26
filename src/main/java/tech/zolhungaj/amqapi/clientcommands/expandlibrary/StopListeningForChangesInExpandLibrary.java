package tech.zolhungaj.amqapi.clientcommands.expandlibrary;

import tech.zolhungaj.amqapi.clientcommands.CommandName;
import tech.zolhungaj.amqapi.clientcommands.EmptyClientCommand;

@EmptyClientCommand
@CommandName("expandLibrary closed")
public final class StopListeningForChangesInExpandLibrary implements ExpandLibraryCommand {
}
