package tech.zolhungaj.amqapi.servercommands.globalstate

import tech.zolhungaj.amqapi.servercommands.CommandType
import tech.zolhungaj.amqapi.servercommands.objects.AvatarDriveContribution
import tech.zolhungaj.amqapi.servercommands.objects.AvatarDriveNomination
import tech.zolhungaj.amqapi.servercommands.objects.Donation

@JvmRecord
@CommandType("new donation")
data class NewDonation(
    val top5Nominations: List<AvatarDriveNomination>,
    val total: Double,
    val top5Monthly: List<AvatarDriveContribution>,
    val top5Weekly: List<AvatarDriveContribution>,
    val donation: Donation,
    val top5AllTime: List<AvatarDriveContribution>
) 