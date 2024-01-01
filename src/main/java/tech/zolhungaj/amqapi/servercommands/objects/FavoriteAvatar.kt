package tech.zolhungaj.amqapi.servercommands.objects

import com.squareup.moshi.Json

@JvmRecord
data class FavoriteAvatar(
    val favoriteId: Int,
    @Json(name = "avatar") val avatarIdentifier: AvatarIdentifier,
    @Json(name = "background") val avatarBackgroundIdentifier: AvatarBackgroundIdentifier
)
