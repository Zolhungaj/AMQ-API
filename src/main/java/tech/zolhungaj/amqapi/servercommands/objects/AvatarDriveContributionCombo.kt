package tech.zolhungaj.amqapi.servercommands.objects

import com.squareup.moshi.Json

@JvmRecord
data class AvatarDriveContributionCombo(
    @Json(name = "avatarName") val avatarName: String,

    @Json(name = "amount") val amount: Double,

    @Json(name = "username") val username: String
) 