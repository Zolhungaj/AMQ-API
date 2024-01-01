package tech.zolhungaj.amqapi.servercommands.objects

import com.squareup.moshi.Json

@JvmRecord
data class ProfileSongCount(
    @Json(name = "hidden") val isHidden: Boolean,
    @Json(name = "adminView") val isAdminView: Boolean,
    @Json(name = "value") val value: Int?
)
