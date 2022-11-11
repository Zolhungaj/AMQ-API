package tech.zolhungaj.amqapi.servercommands.globalstate;

import java.util.List;
import com.squareup.moshi.Json;
import tech.zolhungaj.amqapi.servercommands.Command;
import tech.zolhungaj.amqapi.servercommands.CommandType;
import tech.zolhungaj.amqapi.servercommands.objects.CumulativeAvatarDonation;
import tech.zolhungaj.amqapi.servercommands.objects.Donation;
import tech.zolhungaj.amqapi.servercommands.objects.TopAvatarDonation;

public record NewDonation(
    List<TopAvatarDonation> top5Nominations,
    double total,

    @Json(name = "top5Montly")
    List<CumulativeAvatarDonation> top5Monthly,
    List<CumulativeAvatarDonation> top5Weekly,
    Donation donation,
    List<CumulativeAvatarDonation> top5AllTime
) implements Command {
    @Override
    public String getCommandName() {
        return CommandType.NEW_DONATION.commandName;
    }
}