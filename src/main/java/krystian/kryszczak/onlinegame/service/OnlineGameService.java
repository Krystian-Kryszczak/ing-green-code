package krystian.kryszczak.onlinegame.service;

import io.micronaut.core.annotation.NonNull;
import jakarta.inject.Singleton;
import krystian.kryszczak.onlinegame.schemas.Clan;
import krystian.kryszczak.onlinegame.schemas.Players;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

@Singleton
public final class OnlineGameService {

    public List<List<Clan>> calculate(final @NonNull Players players) {
        players.sortClans();
        return sort(players);
    }

    private List<List<Clan>> sort(final @NonNull Players players) {
        final List<Clan> clans = new LinkedList<>(Arrays.asList(players.clans()));
        final int maxPlayersCount = players.groupCount();
        final List<List<Clan>> result = new LinkedList<>();

        List<Clan> group = new LinkedList<>();
        int groupFreeSlots = maxPlayersCount;

        boolean flag = !clans.isEmpty();
        while (flag) {
            Clan clan = null;
            boolean currentClanCanBeAddedToGroup = false;
            for (final Clan temp : clans) {
                if (temp.getNumberOfPlayers() <= groupFreeSlots) {
                    clan = temp;
                    currentClanCanBeAddedToGroup = true;
                    break;
                }
            }

            if (currentClanCanBeAddedToGroup) {
                group.add(clan);
                groupFreeSlots -= clan.getNumberOfPlayers();
                clans.remove(clan);
            }

            flag = clans.isEmpty();
            if (groupFreeSlots < 1 || !currentClanCanBeAddedToGroup || flag) {
                result.add(group);
                group = new LinkedList<>();
                groupFreeSlots = maxPlayersCount;
            }

            flag = !flag;
        }

        return result;
    }

    private Clan[][] convertToArray(List<List<Clan>> list) {
        return list.stream().map(it -> it.toArray(Clan[]::new)).toArray(Clan[][]::new);
    }
}
