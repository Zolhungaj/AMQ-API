package tech.zolhungaj.amqapi.servercommands.objects

import com.squareup.moshi.Json

@JvmRecord
data class Donation(
    val avatarName: String,
    val amount: Double,
    @Json(name = "username")
    val playerName: String
) 