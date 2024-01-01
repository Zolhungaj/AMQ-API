package tech.zolhungaj.amqapi.servercommands.objects

import com.squareup.moshi.Json

@JvmRecord
data class TicketColorReward(
    @Json(name = "type") override val rewardType: String,
    override val tier: Int,
    override val description: PlayerAvatarDescription,
    @Json(name = "rhythm") override val rhythmReward: Int

) : TicketReward
