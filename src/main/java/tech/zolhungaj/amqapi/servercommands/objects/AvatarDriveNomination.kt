package tech.zolhungaj.amqapi.servercommands.objects

import com.squareup.moshi.Json

@JvmRecord
data class AvatarDriveNomination(
    @Json(name = "name") val avatarName: String,
    @Json(name = "value") val amount: Double
) 