package tech.zolhungaj.amqapi.servercommands.store

import com.squareup.moshi.Json
import tech.zolhungaj.amqapi.servercommands.CommandType
import tech.zolhungaj.amqapi.servercommands.objects.TicketColorReward
import tech.zolhungaj.amqapi.servercommands.objects.TicketEmoteReward
import tech.zolhungaj.amqapi.servercommands.objects.TicketReward
import tech.zolhungaj.amqapi.servercommands.objects.TicketSkinReward

@JvmRecord
@CommandType("ticket roll result")
data class TicketRollResult(
    val rewardList: List<TicketReward>,
    val ticketCount: Int,
    @Json(name = "avatarTokens") val currentAvatarTokens: Int
) {
    fun colorRewardList(): List<TicketColorReward> = rewardList.filterIsInstance<TicketColorReward>()
    fun emoteRewardList(): List<TicketEmoteReward> = rewardList.filterIsInstance<TicketEmoteReward>()
    fun skinRewardList(): List<TicketSkinReward> = rewardList.filterIsInstance<TicketSkinReward>()
}
