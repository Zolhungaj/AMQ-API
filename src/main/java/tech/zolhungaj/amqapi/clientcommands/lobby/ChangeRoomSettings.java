package tech.zolhungaj.amqapi.clientcommands.lobby;

import tech.zolhungaj.amqapi.clientcommands.DirectDataCommand;
import tech.zolhungaj.amqapi.sharedobjects.gamesettings.GameSettings;

public record ChangeRoomSettings(
        GameSettings gameSettings
) implements LobbyCommand, DirectDataCommand {
    @Override
    public String command() {
        return "change game settings";
    }

    @Override
    public Object data() {
        return gameSettings;
    }
}
