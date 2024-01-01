package tech.zolhungaj.amqapi.servercommands.objects

import com.squareup.moshi.Json

@JvmRecord
data class AvatarDriveContribution(
    val amount: Double,
    @Json(name = "name") val playerName: String
)