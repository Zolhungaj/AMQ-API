package tech.zolhungaj.amqapi.servercommands.globalstate;

import tech.zolhungaj.amqapi.servercommands.CommandType;
import tech.zolhungaj.amqapi.servercommands.objects.AvatarDriveContributionCombo;
import tech.zolhungaj.amqapi.servercommands.objects.AvatarDriveNomination;

import java.util.List;

@CommandType("avatar drive changes")
public record AvatarDriveUpdate(
        List<AvatarDriveNomination> top5Nominations,
        List<AvatarDriveContributionCombo> recent8
){}
