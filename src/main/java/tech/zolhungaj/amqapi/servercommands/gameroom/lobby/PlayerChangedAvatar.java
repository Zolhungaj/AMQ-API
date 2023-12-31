package tech.zolhungaj.amqapi.servercommands.gameroom.lobby;

import tech.zolhungaj.amqapi.servercommands.CommandType;
import tech.zolhungaj.amqapi.servercommands.objects.PlayerAvatar;

@CommandType("avatar change")
public record PlayerChangedAvatar(
        int gamePlayerId,
        PlayerAvatar avatar
){}
