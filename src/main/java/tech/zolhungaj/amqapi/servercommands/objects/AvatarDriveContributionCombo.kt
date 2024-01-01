package tech.zolhungaj.amqapi.servercommands.objects;

import com.squareup.moshi.Json;

public record AvatarDriveContributionCombo(

	@Json(name = "avatarName")
	String avatarName,

	@Json(name = "amount")
	double amount,

	@Json(name = "username")
	String username
) {
}