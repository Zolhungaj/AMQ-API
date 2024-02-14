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
    @Json(name = "chatGlowActive") val chatGlowActive: Boolean?,
    @Json(name = "chatColorActive") val chatColorActive: Boolean?,
    @Json(name = "rankedChatGlowUnlocked") val rankedChatGlowUnlocked: Boolean?,
    @Json(name = "rankedChatColorUnlocked") val rankedChatColorUnlocked: Boolean?,
    @Json(name = "level") val level: Int?,
    @Json(name = "avatarProfileImage") val avatarProfileImage: Boolean?,
    @Json(name = "profileEmoteId") val profileImageIdentifiedByEmoteId: Int?,
    @Json(name = "avatar") val profileImageFromAvatar: ProfileAvatar?,
    @Json(name = "creationDate") val creationDate: ProfileCreationDate?,
    @Json(name = "list") val displayedAnimeList: ProfileDisplayedAnimeList?,
    @Json(name = "songCount") val songCount: ProfileSongCount?
)
