package tech.zolhungaj.amqapi.servercommands.objects

import com.squareup.moshi.Json

@JvmRecord
data class ProfileAvailablePlayerBadge(
    @Json(name = "unlocked")
    val isUnlocked: Boolean?,
    val showInChat: Boolean?,
    @Json(name = "slot")
    val slotId: Int?,
    @Json(name = "special")
    val isSpecial: Boolean,
    @Json(name = "fileName")
    val filename: String,
    @Json(name = "name")
    val badgeName: Localisation,
    @Json(name = "id")
    val badgeId: Int,
    @Json(name = "type")
    val badgeType: Int,
    val unlockDescription: Localisation
)
