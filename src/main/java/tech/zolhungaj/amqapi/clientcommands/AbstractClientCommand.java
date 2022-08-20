package tech.zolhungaj.amqapi.clientcommands;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract non-sealed class AbstractClientCommand implements ClientCommand{
    private final ClientCommandType commandType;

    @Override
    public final String command() {
        return commandType.commandName;
    }

    @Override
    public final String type() {
        return commandType.commandType;
    }
}
