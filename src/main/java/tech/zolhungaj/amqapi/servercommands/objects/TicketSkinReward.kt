package tech.zolhungaj.amqapi.servercommands.objects

import com.squareup.moshi.Json
import tech.zolhungaj.amqapi.servercommands.objects.PlayerAvatar.AvatarDescription

@JvmRecord
data class TicketSkinReward(
    @Json(name = "type") override val rewardType: String,
    override val tier: Int,
    override val description: AvatarDescription,
    @Json(name = "rhythm") override val rhythmReward: Int

) : TicketReward
