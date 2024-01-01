package tech.zolhungaj.amqapi.servercommands.objects

import com.squareup.moshi.Json

@JvmRecord
data class PlayerAvatarDescription(
    val avatarId: Int,
    val colorId: Int,
    val characterId: Int,
    val avatarName: String,
    val outfitName: String,
    val colorName: String,
    @Json(name = "backgroundFileName") val backgroundFilename: String,
    val colorActive: Boolean,
    val editor: String?,
    val sizeModifier: Int,
    val optionName: String,
    val optionActive: Boolean,
    val active: Boolean
)
