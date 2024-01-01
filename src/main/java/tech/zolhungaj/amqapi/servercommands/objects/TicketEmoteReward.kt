package tech.zolhungaj.amqapi.servercommands.objects

import com.squareup.moshi.Json

@JvmRecord
data class TicketEmoteReward(
    @Json(name = "type") override val rewardType: String,
    override val tier: Int,
    override val description: Emote,
    @Json(name = "rhythm") override val rhythmReward: Int

) : TicketReward
