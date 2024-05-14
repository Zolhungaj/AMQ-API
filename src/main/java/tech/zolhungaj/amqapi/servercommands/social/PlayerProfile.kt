package tech.zolhungaj.amqapi.servercommands.social

import com.squareup.moshi.Json
import tech.zolhungaj.amqapi.servercommands.CommandType
import tech.zolhungaj.amqapi.servercommands.objects.*

@JvmRecord
@CommandType("player profile")
data class PlayerProfile(
    @Json(name = "error") val error: String?,
    @Json(name = "name") val playerName: String?,
    @Json(name = "originalName") val originalName: String?,
    @Json(name = "guessPercent") val guessPercent: ProfileGuessPercent?,
    @Json(name = "badges") val displayedBadges: List<ProfileDisplayedPlayerBadge>?,
    @Json(name = "allBadges") val availablePlayerBadges: List<ProfileAvailablePlayerBadge>?,
    @Json(name = "nameColors") val availableNameColors: List<NameDecoration>?,
    @Json(name = "nameGlows") val availableNameGlows: List<NameDecoration>?,
    @Json(name = "nameColorClass") val nameColorCssClass: String?,
    @Json(name = "nameGlowClass") val nameGlowCssClass: String?,
    @Json(name = "level") val level: Int?,
    @Json(name = "avatarProfileImage") val avatarProfileImage: Boolean?,
    @Json(name = "profileEmoteId") val profileImageIdentifiedByEmoteId: Int?,
    @Json(name = "avatar") val profileImageFromAvatar: ProfileAvatar?,
    @Json(name = "creationDate") val creationDate: ProfileCreationDate?,
    @Json(name = "list") val displayedAnimeList: ProfileDisplayedAnimeList?,
    @Json(name = "songCount") val songCount: ProfileSongCount?
)
