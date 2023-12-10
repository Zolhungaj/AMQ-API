package tech.zolhungaj.amqapi.servercommands.globalstate;

import java.util.List;
import com.squareup.moshi.Json;
import tech.zolhungaj.amqapi.servercommands.Command;
import tech.zolhungaj.amqapi.servercommands.CommandTypeOld;
import tech.zolhungaj.amqapi.servercommands.objects.AvatarDriveContribution;
import tech.zolhungaj.amqapi.servercommands.objects.Donation;
import tech.zolhungaj.amqapi.servercommands.objects.AvatarDriveNomination;

public record NewDonation(
    List<AvatarDriveNomination> top5Nominations,
    double total,

    @Json(name = "top5Montly")
    List<AvatarDriveContribution> top5Monthly,
    List<AvatarDriveContribution> top5Weekly,
    Donation donation,
    List<AvatarDriveContribution> top5AllTime
) implements Command {
    @Override
    public String commandName() {
        return CommandTypeOld.NEW_DONATION.commandName;
    }
}