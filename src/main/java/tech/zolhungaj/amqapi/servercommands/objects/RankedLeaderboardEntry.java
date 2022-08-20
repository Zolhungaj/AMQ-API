package tech.zolhungaj.amqapi.servercommands.objects;

import org.jetbrains.annotations.NotNull;

public record RankedLeaderboardEntry(
        Integer score,
        String name,
        Integer position
) implements Comparable<RankedLeaderboardEntry>
{
    @Override
    public int compareTo(@NotNull RankedLeaderboardEntry o) {
        return this.position() - o.position();
    }
}
