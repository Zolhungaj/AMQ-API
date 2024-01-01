package tech.zolhungaj.amqapi.servercommands.objects

import com.squareup.moshi.Json

@JvmRecord
data class ProfileDisplayedPlayerBadge(
    @Json(name = "special") val isSpecial: Boolean,
    @Json(name = "fileName") val filename: String,
    @Json(name = "name") val badgeName: String,
    @Json(name = "id") val badgeId: Int,
    @Json(name = "slot") val slotId: Int,
    @Json(name = "type") val badgeType: Int,
    @Json(name = "unlockDescription") val unlockDescription: String
)
