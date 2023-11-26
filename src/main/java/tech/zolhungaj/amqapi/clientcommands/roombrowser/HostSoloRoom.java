package tech.zolhungaj.amqapi.clientcommands.roombrowser;

import tech.zolhungaj.amqapi.clientcommands.CommandName;
import tech.zolhungaj.amqapi.clientcommands.DirectDataCommand;
import tech.zolhungaj.amqapi.sharedobjects.gamesettings.GameSettings;

@DirectDataCommand("settings")
@CommandName("host solo room")
public record HostSoloRoom(GameSettings settings) implements RoomBrowserCommand {
    public HostSoloRoom {
        var settingsBuilder = settings.toBuilder();
        settingsBuilder
                .gameMode(GameSettings.GameMode.SOLO.value)
                .roomName("Solo");
        settings = settingsBuilder.build();
    }
}
