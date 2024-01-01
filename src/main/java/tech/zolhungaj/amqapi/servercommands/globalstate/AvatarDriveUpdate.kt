package tech.zolhungaj.amqapi.servercommands.globalstate

import tech.zolhungaj.amqapi.servercommands.CommandType
import tech.zolhungaj.amqapi.servercommands.objects.AvatarDriveContributionCombo
import tech.zolhungaj.amqapi.servercommands.objects.AvatarDriveNomination

@JvmRecord
@CommandType("avatar drive changes")
data class AvatarDriveUpdate(
    val top5Nominations: List<AvatarDriveNomination>,
    val recent8: List<AvatarDriveContributionCombo>
)
