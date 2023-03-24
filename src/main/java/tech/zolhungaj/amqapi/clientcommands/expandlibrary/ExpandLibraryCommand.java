package tech.zolhungaj.amqapi.clientcommands.expandlibrary;

import tech.zolhungaj.amqapi.clientcommands.ClientCommand;

public sealed interface ExpandLibraryCommand extends ClientCommand permits StopListeningForChangesInExpandLibrary, LoadExpandLibraryAndStartListeningForChanges, SubmitToExpandLibrary {
    @Override
    default String type() {
        return "library";
    }
}
