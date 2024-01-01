package tech.zolhungaj.amqapi.servercommands.objects

import com.squareup.moshi.Json

@JvmRecord
data class RecentTicketRewardDescription(
    val tierId: Int?,
    @Json(name = "name") val rewardName: String?,
    val emoteId: Int?,
    val colorName: String?,
    val editor: String?,
    val colorId: Int?,
    val active: Boolean?,
    val optionActive: Boolean?,
    val backGroundFileName: String?,
    val colorActive: Boolean?,
    val avatarName: String?,
    val avatarId: Int?,
    val outfitName: String?,
    val sizeModifier: Int?,
    val optionName: String?,
    val characterId: Int?
)
