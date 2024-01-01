package tech.zolhungaj.amqapi.servercommands.objects

import com.squareup.moshi.Json

@JvmRecord
data class PlayerAvatarBackgroundDescription(
    val avatarId: Int,
    val colorId: Int,
    val avatarName: String,
    val outfitName: String,
    @Json(name = "backgroundHori") val backgroundHorizontal: String,
    @Json(name = "backgroundVert") val backgroundVertical: String
)
