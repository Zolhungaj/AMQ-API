package tech.zolhungaj.amqapi.servercommands.gameroom;

import tech.zolhungaj.amqapi.servercommands.CommandType;
import tech.zolhungaj.amqapi.servercommands.objects.messages.Buble;

import java.util.List;

@CommandType("game chat update")
public record GameChatUpdate(
        List<GameChatMessage> messages,
        List<Buble> bubles
){}
