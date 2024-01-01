package tech.zolhungaj.amqapi.servercommands.globalstate

import tech.zolhungaj.amqapi.servercommands.CommandType

@JvmRecord
@CommandType("force logoff")
data class ForcedLogoff(
    val reason: String
)
