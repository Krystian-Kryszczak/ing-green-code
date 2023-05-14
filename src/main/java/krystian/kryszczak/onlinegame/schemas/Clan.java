package krystian.kryszczak.onlinegame.schemas;

import io.micronaut.core.annotation.Introspected;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.jetbrains.annotations.NotNull;

@ToString
@Getter
@EqualsAndHashCode
@AllArgsConstructor
@Introspected
public final class Clan implements Comparable<Clan> {
    private final int numberOfPlayers;
    private final int points;

    @Override
    public int compareTo(@NotNull Clan o) {
        int result = -Integer.compare(this.points, o.points);
        if (result != 0) {
            return result;
        }
        return Integer.compare(this.numberOfPlayers, o.numberOfPlayers);
    }
}
