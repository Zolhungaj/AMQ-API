package tech.zolhungaj.amqapi.servercommands.objects

import com.squareup.moshi.Json

@JvmRecord
data class UserSettings(
    val autoHideInserts: Boolean,
    val disableEmojis: Boolean,
    val animeList: Int,
    val voteSkipReplay: Boolean,
    val showTeamAnswersState: Boolean,
    val autoHideEndings: Boolean,
    val useOnHold: Boolean,
    val useRomajiNames: Boolean,
    val equalizeSound: Boolean,
    val shareScore: Boolean,
    val voteSkipGuess: Boolean,
    val usePlanning: Boolean,
    val autoSubmit: Boolean,
    @Json(name = "shareMal") val shareList: Boolean,
    val autoHideOpenings: Boolean,
    val autoHideHighRisk: Boolean,
    @Json(name = "autoSwitchFavoritedAvatars") val autoSwitchFavoriteAvatars: Boolean,
    val useWatched: Boolean,
    val useCompleted: Boolean,
    val useDropped: Boolean,
    val autoHideUnwatched: Boolean
)
