package tech.zolhungaj.amqapi.servercommands.globalstate

import com.squareup.moshi.Json
import tech.zolhungaj.amqapi.servercommands.CommandType
import tech.zolhungaj.amqapi.servercommands.objects.ProfileImageAvatarInfo

@JvmRecord
@CommandType("friend profile image change")
data class FriendProfileImageChange(
    @Json(name = "name") val playerName: String,
    val profileImage: ProfileImageAvatarInfo
)
