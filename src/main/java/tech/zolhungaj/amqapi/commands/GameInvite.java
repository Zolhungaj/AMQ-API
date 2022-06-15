package tech.zolhungaj.amqapi.commands;

public record GameInvite(
        Integer gameId,
        String sender
)implements Command {

    @Override
    public String getName() {
        return CommandType.GAME_INVITE.commandName;
    }
}
