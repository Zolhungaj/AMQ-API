package tech.zolhungaj.amqapi.servercommands.objects;

import org.jetbrains.annotations.NotNull;

public record RankedLeaderboardEntry(
        int score,
        String name,
        int position
) implements Comparable<RankedLeaderboardEntry>
{
    @Override
    public int compareTo(@NotNull RankedLeaderboardEntry o) {
        return this.position() - o.position();
    }
}
