package tech.zolhungaj.amqapi.servercommands.objects

import com.squareup.moshi.Json
import tech.zolhungaj.amqapi.sharedobjects.AnimeList

@JvmRecord
data class ProfileDisplayedAnimeList(
    @Json(name = "hidden")
    val isHidden: Boolean,
    @Json(name = "adminView")
    val isAdminView: Boolean,
    @Json(name = "listId")
    val listSite: AnimeList?,
    @Json(name = "listUserUrl")
    val listSiteUserUrl: String?,
    @Json(name = "listUser")
    val listSiteUsername: String?
)
