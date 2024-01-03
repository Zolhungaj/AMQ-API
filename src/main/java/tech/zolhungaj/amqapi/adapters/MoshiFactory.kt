package tech.zolhungaj.amqapi.adapters

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapters.PolymorphicJsonAdapterFactory
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import tech.zolhungaj.amqapi.constants.RankedSeries
import tech.zolhungaj.amqapi.constants.RankedState
import tech.zolhungaj.amqapi.servercommands.objects.QuestStateWeekSlot
import tech.zolhungaj.amqapi.servercommands.objects.QuestEventStateType
import tech.zolhungaj.amqapi.servercommands.objects.*
import tech.zolhungaj.amqapi.servercommands.objects.expand.ExpandSongStatus
import tech.zolhungaj.amqapi.sharedobjects.AnimeList
import java.lang.reflect.Type

class MoshiFactory {
    companion object {
        val MOSHI: Moshi = Moshi.Builder()
            .add(CustomBooleanAdapter())
            .add(CustomKotlinBooleanAdapter())
            .add(CustomLocalDateAdapter())
            .add(CustomOffsetDateTimeAdapter())
            .add(PlayerLeftAdapter())
            .add(SpectatorLeftAdapter())
            .add(
                PolymorphicJsonAdapterFactory.of(TicketReward::class.java, "rewardType")
                    .withSubtype(TicketSkinReward::class.java, "avatar")
                    .withSubtype(TicketColorReward::class.java, "color")
                    .withSubtype(TicketEmoteReward::class.java, "emote")
            )
            .add(PlayerStatus::class.java, IntegerEnumJsonAdapter.create(PlayerStatus::class.java))
            .add(
                QuestEventStateType::class.java, IntegerEnumJsonAdapter.create(
                    QuestEventStateType::class.java))
            .add(
                RankedSeries::class.java, IntegerEnumJsonAdapter.create(
                    RankedSeries::class.java).withUnknownFallback(null))
            .add(
                RankedState::class.java, IntegerEnumJsonAdapter.create(
                    RankedState::class.java))
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
            .add(
                QuestStateWeekSlot::class.java,
                QuestStateWeekSlotAdapter()
            )
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