package tech.zolhungaj.amqapi.clientcommands.expandlibrary;

import tech.zolhungaj.amqapi.clientcommands.EmptyClientCommand;

public final class ExpandLibraryGetQuestions implements EmptyClientCommand, ExpandLibraryCommand {
    @Override
    public String command() {
        return "expandLibrary questions";
    }
}
