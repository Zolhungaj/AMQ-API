package tech.zolhungaj.amqapi.servercommands.objects

import com.squareup.moshi.Json

@JvmRecord
data class StoreAvatar(
        val colorName: String,
        val outfitId: Int,
        val patreonTierToUnlock: Int?,
        val notePrice: Int,
        val lore: String,
        val limited: Boolean,
        val artist: String,
        val badgeName: String,
        @JvmField @Json(name = "backgroundVert") val backgroundVertical: String,
        val active: Boolean,
        val colors: List<StoreAvatarColor>,
        val defaultColorId: Int,
        val badgeFileName: String,
        val avatarName: String,
        val defaultAvatar: Boolean,
        val avatarId: Int,
        val world: String,
        val tierId: Int?,
        val realMoneyPrice: Int,
        val outfitName: String,
        val exclusive: Boolean,
        val sizeModifier: Int,
        val optionName: String
)

