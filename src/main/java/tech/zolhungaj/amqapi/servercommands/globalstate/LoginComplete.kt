package tech.zolhungaj.amqapi.servercommands.globalstate

import com.squareup.moshi.Json
import tech.zolhungaj.amqapi.servercommands.CommandType
import tech.zolhungaj.amqapi.servercommands.objects.*
import java.time.Instant

@JvmRecord
@CommandType("login complete")
data class LoginComplete(
    val gameAdmin: Boolean,
    val questDescriptions: List<QuestDescription>,
    val savedQuizSettings: List<SavedQuizSetting>,
    val patreonBadgeInfo: PatreonBadgeInfo,
    val rewardAlert: RewardAlert?,
    val driveTotal: Double,
    val top5AllTime: List<AvatarDriveContribution>,
    val displayArtContestPopUp: Boolean,
    @Json(name = "top5AvatarNominatios") val top5AvatarNominations: List<AvatarDriveNomination>,
    val patreonId: Int?,
    val avatarUnlockCount: Map<String, Int>,
    val unlockedDesigns: Map<String, Map<String, Boolean>>,
    val recentTicketRewards: List<RecentTicketReward>,
    val rankedState: RankedState,
    val defaultAvatars: List<DefaultAvatar>,
    val backerLevel: Int,
    val settings: UserSettings,
    val level: Int,
    val unlockedEmoteIds: List<Int>,
    @Json(name = "malName") val mal: String?,
    val aniList: String?,
    val kitsu: String?,
    val malLastUpdate: String?,
    val aniListLastUpdate: String?,
    val kitsuLastUpdate: String?,
    val friends: List<CurrentFriendEntry>,
    val rankedChampions: RankedChampions,
    val nameChangeTokens: Int,
    val blockedPlayers: List<String>,
    val emoteGroups: List<EmoteGroup>,
    val tickets: Int,
    @Json(name = "top5Montly") val top5Monthly: List<AvatarDriveContribution>,
    val serverStatuses: List<FileServerStatus>,
    val topAdmin: Boolean,
    val useRomajiNames: Boolean,
    val questTokenProgress: Int,
    @Json(name = "tagInfo") val tags: List<AnimeTag>,
    val rankedLeaderboards: RankedLeaderboard,
    val rollTargets: List<RollTarget>,
    val xpInfo: XPInfo,
    val credits: Int,
    @Json(name = "genreInfo") val genres: List<AnimeGenre>,
    val tutorial: Int,
    @Json(name = "canReconnectGame") val canReconnectToGame: Boolean,
    val recentDonations: List<Donation>,
    val avatarTokens: Int,
    val freeDonation: Boolean,
    val characterUnlockCount: Map<String, Int>,
    val customEmojis: List<PersonalCustomEmoji>,
    val avatar: PlayerAvatar,
    val patreonDesynced: Boolean?,
    val rhythm: Int,
    val videoHostNames: List<String>,
    val patreonClaimed: Boolean,
    val favoriteAvatars: List<FavoriteAvatar>,
    val top5Weekly: List<AvatarDriveContribution>,
    val discordClaimed: Boolean,
    val expandCount: Int,
    val recentEmotes: List<RecentEmote>,
    @Json(name = "saleTax") val salesTax: SalesTax,
    @Json(name = "self") val selfName: String,
    val badgeLevel: Int,
    val guestAccount: Boolean,
    val tutorialState: TutorialState,
    val nexusBuffs: List<NexusBuff>,
    @Json(name = "canReconnectNexus") val canReconnectToNexus: Boolean,
    @Json(name = "nexusStatBaseMax") val nexusStatsBaseMaximum: Int,
    //val eventInfo: Any, //TODO
    //val resonanceState: Any, //TODO
    //val giftMessages: List<Any>, //TODO
    val hostOrder: List<String>,
    val latestQuizSettings: LatestQuizSettings,
    val saleInfo: SaleInfo,
    val uniqueBackgrounds: List<UniqueBackground>,
) {
    fun aniListLastUpdateInstant(): Instant? {
        return aniListLastUpdate?.let { Instant.parse(it) }
    }

    fun kitsuLastUpdateInstant(): Instant? {
        return kitsuLastUpdate?.let { Instant.parse(it) }
    }

    fun malLastUpdateInstant(): Instant? {
        return malLastUpdate?.let { Instant.parse(it) }
    }
}
