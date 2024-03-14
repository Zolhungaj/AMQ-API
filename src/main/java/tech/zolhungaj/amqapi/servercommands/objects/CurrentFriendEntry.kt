package tech.zolhungaj.amqapi.servercommands.objects


@JvmRecord
data class CurrentFriendEntry(
    val avatarProfileImage: Boolean?,
    val avatarName: String,
    val colorName: String,
    val profileEmoteId: Int?,
    val name: String,
    val online: Boolean,
    val outfitName: String,
    val optionActive: Boolean,
    val optionName: String,
    val gameState: PlayerGameState?,
    val status: PlayerStatus = PlayerStatus.OFFLINE,
    val animated: Boolean
)
