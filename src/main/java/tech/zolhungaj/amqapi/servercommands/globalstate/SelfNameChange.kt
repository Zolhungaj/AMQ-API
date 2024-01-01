package tech.zolhungaj.amqapi.servercommands.globalstate

import tech.zolhungaj.amqapi.servercommands.CommandType

@JvmRecord
@CommandType("self name changed")
data class SelfNameChange(val newName: String)
