package tech.zolhungaj.amqapi.servercommands.objects

@JvmRecord
data class DefaultAvatar(
    val characterId: Int,
    val avatars: List<StoreAvatar>
)
