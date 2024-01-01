package tech.zolhungaj.amqapi.servercommands.globalstate

import tech.zolhungaj.amqapi.servercommands.CommandType

@JvmRecord
@CommandType("online player count change")
data class OnlinePlayerCountChange(
    val count: Int
)
