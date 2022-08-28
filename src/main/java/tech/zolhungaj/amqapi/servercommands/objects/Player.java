package tech.zolhungaj.amqapi.servercommands.objects;

import com.squareup.moshi.Json;
import lombok.Data;
import org.jetbrains.annotations.Nullable;

@Data
public class Player {
    @Json(name = "name")
    private String playerName;
    private Integer gamePlayerId;
    private Integer level;
    private PlayerAvatar avatar;
    private Boolean ready;
    private Boolean inGame;
    @Nullable
    private Integer teamNumber;
}
