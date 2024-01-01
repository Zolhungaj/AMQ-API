package tech.zolhungaj.amqapi.servercommands.objects

import com.squareup.moshi.Json
import java.util.*

@JvmRecord
data class ProfileImageAvatarInfo(
    @Json(name = "profileEmoteId") val emoteId: Int?,
    val avatarName: String,
    val outfitName: String,
    val optionName: String,
    val optionActive: Boolean,
    val colorName: String,
    val editor: String?,
    val colorId: Int,
    val active: Boolean,
    val backgroundFileName: String,
    val colorActive: Boolean,
    val avatarId: Int,
    val sizeModifier: Int,
    val characterId: Int
)
