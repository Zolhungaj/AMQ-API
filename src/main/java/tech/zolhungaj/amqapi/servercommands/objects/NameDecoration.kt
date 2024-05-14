package tech.zolhungaj.amqapi.servercommands.objects

@JvmRecord
data class NameDecoration(
    val option: NameDecorationOption,
    val unlocked: Boolean,
    val active: Boolean,
    )
