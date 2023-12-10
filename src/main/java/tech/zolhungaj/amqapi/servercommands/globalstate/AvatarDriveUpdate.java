package tech.zolhungaj.amqapi.servercommands.globalstate;

import tech.zolhungaj.amqapi.servercommands.Command;
import tech.zolhungaj.amqapi.servercommands.CommandTypeOld;
import tech.zolhungaj.amqapi.servercommands.objects.AvatarDriveContributionCombo;
import tech.zolhungaj.amqapi.servercommands.objects.AvatarDriveNomination;

import java.util.List;

public record AvatarDriveUpdate(
        List<AvatarDriveNomination> top5Nominations,
        List<AvatarDriveContributionCombo> recent8
) implements Command {
    @Override
    public String commandName() {
        return CommandTypeOld.AVATAR_DRIVE_UPDATE.commandName;
    }
}
