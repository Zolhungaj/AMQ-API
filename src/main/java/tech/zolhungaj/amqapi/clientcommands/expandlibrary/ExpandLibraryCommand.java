package tech.zolhungaj.amqapi.clientcommands.expandlibrary;

import tech.zolhungaj.amqapi.clientcommands.ClientCommand;

public sealed interface ExpandLibraryCommand extends ClientCommand permits ExpandLibraryClose, ExpandLibraryGetQuestions, ExpandLibrarySubmit {
    @Override
    default String type() {
        return "library";
    }
}
