package tech.zolhungaj.amqapi.clientcommands.roombrowser;

import tech.zolhungaj.amqapi.clientcommands.DirectDataCommand;
import tech.zolhungaj.amqapi.sharedobjects.gamesettings.GameSettings;

public record HostRoom(
        GameSettings settings,
        Boolean isSoloRoom
) implements RoomBrowserCommand, DirectDataCommand {
    public HostRoom{
        if(isSoloRoom == null){
            isSoloRoom = false;
        }
        if (isSoloRoom) {
            settings = settings.withGameMode(GameSettings.GameMode.SOLO.value).withRoomName("Solo");
        } else {
            settings = settings.withGameMode(GameSettings.GameMode.MULTIPLAYER.value);
        }
    }
    public HostRoom(GameSettings settings){
        this(settings, null);
    }

    @Override
    public GameSettings data() {
        return settings;
    }

    @Override
    public String command() {
        if (Boolean.TRUE.equals(isSoloRoom)) {
            return "host solo room";
        } else {
            return "host room";
        }
    }
}
