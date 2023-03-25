package tech.zolhungaj.amqapi.clientcommands.lobby;

import tech.zolhungaj.amqapi.clientcommands.ClientCommand;

public sealed interface LobbyCommand extends ClientCommand permits ChangeRoomSettings, JoinQueue, JoinTeam, Kick, LeaveQueue, LeaveRoom, LeaveTeam, MarkSelfAsAfkAndSurrenderHostStatusToSomeoneElse, MovePlayerToSpectator, MoveSelfToPlayer, PromoteToHost, SendChatMessage, SetSelfReadyStatus, ShuffleTeams, StartGame {
    @Override
    default String type() {
        return "lobby";
    }
}
