package tech.zolhungaj.amqapi.clientcommands.roombrowser;

import tech.zolhungaj.amqapi.clientcommands.CommandName;
import tech.zolhungaj.amqapi.clientcommands.DirectDataCommand;
import tech.zolhungaj.amqapi.sharedobjects.gamesettings.GameSettings;

@DirectDataCommand("settings")
@CommandName("host room")
public record HostMultiplayerRoom(
        GameSettings settings
) implements RoomBrowserCommand {
    public HostMultiplayerRoom {
        var settingsBuilder = settings.toBuilder();
        settingsBuilder.gameMode(GameSettings.GameMode.MULTIPLAYER.value);
        settings = settingsBuilder.build();
    }
}
