package tech.zolhungaj.amqapi.clientcommands.lobby;

import com.fasterxml.jackson.annotation.JsonProperty;
import tech.zolhungaj.amqapi.clientcommands.CommandName;
import tech.zolhungaj.amqapi.sharedobjects.gamesettings.GameSettings;

@CommandName("change game settings")
public record ChangeRoomSettings(
        //Note: if we were nice we would be using a Map<String, Object> here to only send a delta,
        // but since this API doesn't have state that's a bit hard to do
        @JsonProperty("settingChanges")
        GameSettings newSettings,
        Boolean communityMode
) implements LobbyCommand {
    public ChangeRoomSettings(GameSettings newSettings) {
        this(newSettings, false);
    }
}
