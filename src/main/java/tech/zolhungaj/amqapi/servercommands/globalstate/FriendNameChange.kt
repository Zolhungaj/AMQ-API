package tech.zolhungaj.amqapi.servercommands.globalstate

import tech.zolhungaj.amqapi.servercommands.CommandType

@JvmRecord
@CommandType("friend name change")
data class FriendNameChange(
    val newName: String,
    val oldName: String
)
