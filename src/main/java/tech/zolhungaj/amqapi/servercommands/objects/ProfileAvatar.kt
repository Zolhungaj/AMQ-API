package tech.zolhungaj.amqapi.servercommands.objects

import com.squareup.moshi.Json

@JvmRecord
data class ProfileAvatar(
    val avatarName: String,
    val colorName: String,
    val outfitName: String,
    @Json(name = "optionActive") val optionActive: Boolean,
    val optionName: String
)
