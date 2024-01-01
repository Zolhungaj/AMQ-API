package tech.zolhungaj.amqapi.servercommands.globalstate

import tech.zolhungaj.amqapi.servercommands.CommandType

@JvmRecord
@CommandType("popout message")
data class PopoutMessage(
    val header: String,
    val message: String
)
