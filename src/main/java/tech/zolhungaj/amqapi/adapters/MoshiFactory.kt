package tech.zolhungaj.amqapi.adapters

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapters.PolymorphicJsonAdapterFactory
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import tech.zolhungaj.amqapi.constants.AmqRanked
import tech.zolhungaj.amqapi.constants.AmqRanked.RankedSeries
import tech.zolhungaj.amqapi.servercommands.globalstate.LoginComplete.QuestDescription.QuestStateWeekSlot
import tech.zolhungaj.amqapi.servercommands.globalstate.LoginComplete.QuestDescription.QuestStateWeekSlotAdapter
import tech.zolhungaj.amqapi.servercommands.globalstate.NewQuestEvents.QuestEventState
import tech.zolhungaj.amqapi.servercommands.objects.AvatarPose
import tech.zolhungaj.amqapi.servercommands.objects.ListStatus
import tech.zolhungaj.amqapi.servercommands.objects.PlayerStatus
import tech.zolhungaj.amqapi.servercommands.objects.SongType
import tech.zolhungaj.amqapi.servercommands.objects.expand.ExpandSongStatus
import tech.zolhungaj.amqapi.servercommands.store.TicketRollResult.*
import tech.zolhungaj.amqapi.sharedobjects.AnimeList
import java.lang.reflect.Type

class MoshiFactory {
    companion object {
        val MOSHI: Moshi = Moshi.Builder()
            .add(CustomBooleanAdapter())
            .add(CustomKotlinBooleanAdapter())
            .add(CustomOptionalBooleanAdapter())
            .add(CustomOptionalStringAdapter())
            .add(CustomLocalDateAdapter())
            .add(CustomOffsetDateTimeAdapter())
            .add(OptionalFactory())
            .add(PlayerLeftAdapter())
            .add(SpectatorLeftAdapter())
            .add(
                PolymorphicJsonAdapterFactory.of(Reward::class.java, "rewardType")
                    .withSubtype(SkinReward::class.java, "avatar")
                    .withSubtype(ColorReward::class.java, "color")
                    .withSubtype(EmoteReward::class.java, "emote")
            )
            .add(PlayerStatus::class.java, IntegerEnumJsonAdapter.create(PlayerStatus::class.java))
            .add(QuestEventState::class.java, IntegerEnumJsonAdapter.create(QuestEventState::class.java))
            .add(RankedSeries::class.java, IntegerEnumJsonAdapter.create(RankedSeries::class.java))
            .add(AmqRanked.RankedState::class.java, IntegerEnumJsonAdapter.create(AmqRanked.RankedState::class.java))
            .add(
                ExpandSongStatus::class.java,
                IntegerEnumJsonAdapter.create(ExpandSongStatus::class.java)
                    .withUnknownFallback(ExpandSongStatus.UNKNOWN)
            )
            .add(
                SongType::class.java,
                IntegerEnumJsonAdapter.create(SongType::class.java)
                    .withUnknownFallback(SongType.UNKNOWN)
            )
            .add(AnimeList::class.java, IntegerEnumJsonAdapter.create(AnimeList::class.java))
            .add(AvatarPose::class.java, IntegerEnumJsonAdapter.create(AvatarPose::class.java))
            .add(ListStatus::class.java, IntegerEnumJsonAdapter.create(ListStatus::class.java))
            .add(QuestStateWeekSlot::class.java, QuestStateWeekSlotAdapter())
            .addLast(KotlinJsonAdapterFactory())
            .build()
    }

    @JvmOverloads
    fun create(adapters: List<Any> = emptyList(), typeSpecificAdapters: List<Pair<Type, JsonAdapter<Any>>> = emptyList()): Moshi {
        val builder = MOSHI.newBuilder()
        adapters.forEach {
            builder.add(it)
        }
        typeSpecificAdapters.forEach {
            builder.add(it.first, it.second)
        }
        return builder.build()
    }
}