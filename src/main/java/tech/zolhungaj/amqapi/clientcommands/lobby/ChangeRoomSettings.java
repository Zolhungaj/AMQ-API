package tech.zolhungaj.amqapi.clientcommands.lobby;

import tech.zolhungaj.amqapi.clientcommands.CommandName;
import tech.zolhungaj.amqapi.sharedobjects.gamesettings.GameSettings;

@CommandName("change game settings")
public record ChangeRoomSettings(
        GameSettings newSettings,
        Boolean communityMode
) implements LobbyCommand {
    public ChangeRoomSettings(GameSettings newSettings) {
        this(newSettings, false);
    }
}
