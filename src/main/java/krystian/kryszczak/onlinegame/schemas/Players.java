package krystian.kryszczak.onlinegame.schemas;

import java.util.Arrays;

public record Players(int groupCount, Clan[] clans) {
    public void sortClans() {
        Arrays.sort(clans);
    }
}
