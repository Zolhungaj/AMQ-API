package tech.zolhungaj.amqapi.servercommands.objects

import com.squareup.moshi.Json

interface TicketReward {
    @Json(name = "type")
    val rewardType: String //avatar, color, emote(?)
    val tier: Int
    val rhythmReward: Int
    val description: Any
}
