package krystian.kryszczak.onlinegame.endpoint;

import io.micronaut.core.annotation.NonNull;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import io.micronaut.scheduling.TaskExecutors;
import io.micronaut.scheduling.annotation.ExecuteOn;
import krystian.kryszczak.onlinegame.schemas.Clan;
import krystian.kryszczak.onlinegame.schemas.Players;
import krystian.kryszczak.onlinegame.service.OnlineGameService;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
@ExecuteOn(TaskExecutors.IO)
@Controller(OnlineGameEndpoint.url)
public final class OnlineGameEndpoint {
    public static final String url = "/onlinegame/calculate";
    private final OnlineGameService onlineGameService;

    @Post
    public List<List<Clan>> calculate(final @NonNull @Body Players players) {
        return onlineGameService.calculate(players);
    }
}
