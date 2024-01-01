package tech.zolhungaj.amqapi.servercommands.globalstate;

import com.squareup.moshi.Json;
import tech.zolhungaj.amqapi.servercommands.CommandType;
import tech.zolhungaj.amqapi.servercommands.objects.AvatarDriveContribution;
import tech.zolhungaj.amqapi.servercommands.objects.AvatarDriveNomination;
import tech.zolhungaj.amqapi.servercommands.objects.Donation;

import java.util.List;

@CommandType("new donation")
public record NewDonation(
    List<AvatarDriveNomination> top5Nominations,
    double total,

    @Json(name = "top5Montly")
    List<AvatarDriveContribution> top5Monthly,
    List<AvatarDriveContribution> top5Weekly,
    Donation donation,
    List<AvatarDriveContribution> top5AllTime
){}