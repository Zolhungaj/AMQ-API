package tech.zolhungaj.amqapi.servercommands.objects

@JvmRecord
data class TutorialState(
    val bossPlayed: Boolean,
    val nexusRuneSetup: Boolean,
    val initialShow: Boolean,
    val avatarCompleted: Boolean,
    val socialCompleted: Boolean,
    val nexusWorkshop: Boolean,
    val rankedCompleted: Boolean,
    val nexusDungeonTutorial: Boolean,
    val firstGameComplete: Boolean,
    val teamPlayed: Boolean,
    val livesPlayed: Boolean,
    val lootingPlayed: Boolean,
    val speedPlayed: Boolean
)